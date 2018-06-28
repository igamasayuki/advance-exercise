package com.example.ec_201804d.controller;

public class KanaChangeService {
	/*
	   * ぁあぃいぅうぇえぉおかがきぎくぐけげこごさざしじすずせぜそぞ
	   * ただちぢっつづてでとどなにぬねのはばぱひびぴふぶぷへべぺほぼぽ
	   * まみむめもゃやゅゆょよらりるれろゎわゐゑをん
	   * 
	   * ァアィイゥウェエォオカガキギクグケゲコゴサザシジスズセゼソゾ
	   * タダチヂッツヅテデトドナニヌネノハバパヒビピフブプヘベペホボポ
	   * マミムメモャヤュユョヨラリルレロヮワヰヱヲンヴヵヶ
	   */

	private static String ToKatakana(String s) {
		StringBuffer sb = new StringBuffer(s);
		for (int i = 0; i < sb.length(); i++) {
			char c = sb.charAt(i);
			if (c >= 'ぁ' && c <= 'ん') {
				sb.setCharAt(i, (char) (c - 'ぁ' + 'ァ'));
			}
		}
		return sb.toString();
	}
	
	private static String toHiragana(String s) {
	    StringBuffer sb = new StringBuffer(s);
	    for (int i = 0; i < sb.length(); i++) {
	      char c = sb.charAt(i);
	      if (c >= 'ァ' && c <= 'ン') {
	        sb.setCharAt(i, (char)(c - 'ァ' + 'ぁ'));
	      } else if (c == 'ヵ') {
	        sb.setCharAt(i, 'か');
	      } else if (c == 'ヶ') {
	        sb.setCharAt(i, 'け');
	      } else if (c == 'ヴ') {
	        sb.setCharAt(i, 'う');
	        sb.insert(i + 1, '゛');
	        i++;
	      }
	    }
	    return sb.toString();    
	  }
	
	public static String[] changeWord(String keyword) {
		String[] keywords = new String[2];
		keywords[0] = toHiragana(keyword);
		keywords[1] = ToKatakana(keyword);
		return keywords;
	}
}
