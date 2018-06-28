package com.example.ec_201804d.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.ec_201804d.domain.AdminUser;
import com.example.ec_201804d.domain.LoginAdminUser;
import com.example.ec_201804d.repository.AdminUserRepository;

/**
 * ログイン後の管理者情報に権限情報を付与するサービスクラス.
 * 
 * @author hibiki.ono
 *
 */
@Service
public class AdminUserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private AdminUserRepository adminUserRepository;
	@Autowired
	private HttpSession session;
	
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
		AdminUser adminUser = adminUserRepository.findByMailAddress(email);
		System.out.println("AdminUserDetailsService");
		if(adminUser == null) {
			throw new UsernameNotFoundException("そのEmailは登録されていません");
		}
		Collection<GrantedAuthority> authorityList = new ArrayList<>();
		authorityList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		
		//ログイン時の処理
		calender();
		
		LoginAdminUser loginAdminUser = new LoginAdminUser(adminUser, authorityList);
		System.out.println(loginAdminUser);
		return loginAdminUser;
	}
	
	private void calender() {
		
		int nowYear = LocalDate.now().getYear();
		int nowMonth = LocalDate.now().getMonthValue();
		int nowDay = LocalDate.now().getDayOfMonth();
		
		//全ての週を入れるリスト
		List<List<String>> weekList = new ArrayList<>();
		// 1週間分の日にちを入れるリスト
		List<String> dayList = new ArrayList<>();
		// 入力した年月の1日のLocalDateオブジェクトを生成
		LocalDate firstDay = LocalDate.of(nowYear, nowMonth, 1);
		// 1日の曜日を取得
		DayOfWeek dayOfWeek = firstDay.getDayOfWeek();
		int dayOfWeekValue = dayOfWeek.getValue();
		final int SUNDAY = 7;
		if (dayOfWeekValue != SUNDAY) {
			// 日曜日で始まるとき以外は1日までに空文字を入力
			for (int i = 0; i < dayOfWeekValue; i++) {
				dayList.add("");
			}
		}
		// 入力した年月の最後の日のLocalDateオブジェクトを生成
		LocalDate lastDay = firstDay.with(TemporalAdjusters.lastDayOfMonth());

		// リストに情報を入れる
		for (int i = dayList.size(); true; i++) {
			String day = String.valueOf(firstDay.getDayOfMonth());
			dayList.add(day);
			if (i == 6 && String.valueOf(lastDay.getDayOfMonth()).equals(day) == false) {
				weekList.add(dayList);
				i = -1;
				dayList = new ArrayList<>();
			}

			if (firstDay.equals(lastDay)) {
				if (dayList.size() < SUNDAY) {
					for (i = dayList.size(); i < SUNDAY; i++) {
						dayList.add("");
					}
				}
				weekList.add(dayList);
				break;
			}
			firstDay = firstDay.plusDays(1);
		}

		session.setAttribute("year", nowYear);
		session.setAttribute("month", nowMonth);
		session.setAttribute("weekList", weekList);
		session.setAttribute("nowDay", nowDay);
	}
}
