package com.example.ec_201804d;



import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class JobStartEndListner extends JobExecutionListenerSupport{

	private final JdbcTemplate jdbcTemplate;
	   
	  @Autowired
	  public JobStartEndListner(JdbcTemplate jdbcTemplate) {
	    this.jdbcTemplate = jdbcTemplate;
	  }
	   
	  // ステップの開始前に実行
	  @Override
	  public void beforeJob(JobExecution jobExecution) {
	    super.beforeJob(jobExecution);
	    System.out.println("開始");
	  } 
	 
	  // ステップの終了後に実行
	  @Override
	  public void afterJob(JobExecution jobExecution) {
	    super.afterJob(jobExecution);
	    System.out.println("終了");
	  }
}
