package com.example.ec_201804d;



import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.example.ec_201804d.domain.Item;
import com.example.ec_201804d.domain.Order;

/**
 * 商品をDBに登録するバッチ処理を行うコントローラクラス.
 * 
 * @author hibiki.ono
 *
 */
@Transactional
@Configuration
@EnableBatchProcessing
public class DoBatchItemRegister {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	public DataSource dataSource;

	// Reader
	/**
	 * csvファイルからデータを取得.
	 *  
	 * @return
	 */
	@Bean
	public FlatFileItemReader<Item> reader() {

		FlatFileItemReader<Item> reader = new FlatFileItemReader<Item>();
		reader.setEncoding("SJIS");
		reader.setResource(new ClassPathResource("items.csv"));
		reader.setLineMapper(new DefaultLineMapper<Item>() {
			{
				setLineTokenizer(new DelimitedLineTokenizer() {
					{
						setNames(new String[] { "name", "description", "price", "imagePath", "deleted" });
					}
				});
				setFieldSetMapper(new BeanWrapperFieldSetMapper<Item>() {
					{
						setTargetType(Item.class);
					}
				});
			}
		});

		return reader;
	}
	
	private static final String orderSql = "SELECT id," + "order_number," + "user_id,status," + "total_price,"
			+ "order_date,delivery_name,delivery_email,delivery_zip_code,delivery_address,delivery_tel "
			+ "FROM orders ORDER BY id";

	/**
	 * DBから注文情報を取得.
	 * 
	 * @return
	 */
	@Bean
	public ItemReader<Order> readerDB() {
		JdbcCursorItemReader<Order> readerDB = new JdbcCursorItemReader<>();
		readerDB.setDataSource(dataSource);
		readerDB.setSql(orderSql);
		readerDB.setRowMapper(new BeanPropertyRowMapper<>(Order.class));
		
		return readerDB;
	}

	// Processor
	/**
	 * 商品データを加工.
	 * 
	 * @return
	 */
	@Bean
	public ItemProcessoritem processor() {
		return new ItemProcessoritem();
	}

	// Writer
	/**
	 * 商品情報をINSERT.
	 * 
	 * @return
	 */
	@Bean
	public JdbcBatchItemWriter<Item> writerDB() {
		JdbcBatchItemWriter<Item> writerDB = new JdbcBatchItemWriter<Item>();
		writerDB.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Item>());
		writerDB.setSql(
				"INSERT INTO items (name, description, price, imagePath, deleted) VALUES (:name, :description, :price, :imagePath, :deleted)");
		writerDB.setDataSource(dataSource);
		return writerDB;
	}

	/**
	 * 注文情報をcsvファイルに書き込み.
	 * 
	 * @return
	 */
	@Bean
	public FlatFileItemWriter<Order> writerCsv() {
		FlatFileItemWriter<Order> writerCsv = new FlatFileItemWriter<Order>();
		writerCsv.setResource(new FileSystemResource("C://env//EC//order_201804.csv"));
		writerCsv.setEncoding("SJIS");
		writerCsv.setAppendAllowed(true);
		writerCsv.setLineAggregator(new ECAggregator());
				
		return writerCsv;
	}

	/**
	 * DB情報をセット.
	 * 
	 * @return
	 */
	@Bean
	public JobExecutionListener listener() {
		return new JobStartEndListner(new JdbcTemplate(dataSource));
	}

	// ステップ１
	@Bean
	public Step step1() {
		return stepBuilderFactory
				.get("step1")
				.<Item, Item>chunk(10)
				.reader(reader())
				.processor(processor())
				.writer(writerDB())
				.build();
	}

	// ステップ２
	@Bean
	public Step step2() {
		return stepBuilderFactory
				.get("step2")
				.<Order, Order>chunk(10)
				.reader(readerDB())
				.writer(writerCsv())
				.build();
	}

	// ジョブ
	@Bean
	public Job testJob() {
		return jobBuilderFactory
				.get("testJob")
				.incrementer(new RunIdIncrementer())
				.listener(listener())
				.flow(step1())
				.next(step2())
				.end()
				.build();
	}

}
