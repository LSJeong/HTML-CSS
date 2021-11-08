package com.yedam.member;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemDAO extends DAO {
	//스케줄 삭제 메소드(title)
	public boolean remSchedule(String title) {
		connect();
		String sql = "delete from schedule where title = ?";	
		try {
			psmt= conn.prepareStatement(sql);
			psmt.setString(1, title);
			int r = psmt.executeUpdate();
			if(r>0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	//스케줄 등록 메소드(title,start,end);
	public boolean addSchedule(String title, String start, String end) {
		connect();
		String sql = "insert into schedule values(?,?,?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, title);
			psmt.setString(2, start);
			psmt.setString(3, end);
			int r = psmt.executeUpdate();
			if(r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return false;
	}	
	
	
	
	
	//fullCalendar용 샘플 데이터
	//List<Map<String, String>>
	public List<Map<String,String>> getSchedules(){
		List<Map<String,String>> schedules = new ArrayList<Map<String,String>>();
		connect();
		String sql = "select * from schedule";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("title", rs.getString("title"));
				map.put("start", rs.getString("start_date"));
				map.put("end", rs.getString("end_date"));
				schedules.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return schedules;
	}
	
	
	// 부서별 인원
	public Map<String, Integer> getMemberByDept(){
		connect();
		String sql = "select 'Administration', 1 from dual\r\n"
				+ "union all\r\n"
				+ "select 'Accounting', 2 from dual\r\n"
				+ "union all\r\n"
				+ "select 'IT', 3 from dual\r\n"
				+ "union all\r\n"
				+ "select 'Executive', 3 from dual\r\n"
				+ "union all\r\n"
				+ "select 'Shipping', 5 from dual\r\n"
				+ "union all\r\n"
				+ "select 'Sales', 3 from dual\r\n"
				+ "union all\r\n"
				+ "select 'Marketing', 2 from dual";
		Map<String, Integer> map = new HashMap<String, Integer>();
		try {
			stmt = conn.createStatement();
			rs= stmt.executeQuery(sql);
			while(rs.next()) {
				map.put(rs.getString(1), rs.getInt(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return map;
		
	}
	
	//페이지별 토탈 건수 가져오기
	public int getTotalCount() {
		connect();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(1) from member");
			if(rs.next()) {
				int r = rs.getInt(1);
				System.out.println("조회건수: " + r);
				return r;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}finally {
			disconnect();
		}
		return 0;
	}
	
	
	
	//페이지별 데이터 조회
	public List<MemberVO> getMemberByPage(String page){
		connect();
		List<MemberVO> list = new ArrayList<MemberVO>();
		String sql = "select b.* from\r\n"
				+ "(select rownum as num, a.* from\r\n"
				+ "(select * from member order by 1) a) b\r\n"
				+ "where b.num >= ? and b.num <= ?";
		int start = (Integer.parseInt(page) - 1) * 10 + 1;
		int end = Integer.parseInt(page) * 10 ;
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, start);
			psmt.setInt(2, end);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				MemberVO mem = new MemberVO();
				mem.setAddress(rs.getString("address")); // DB 칼럼이름이랑 똑같이
				mem.setBirthDate(rs.getString("birth_date"));
				mem.setGender(rs.getString("gender"));
				mem.setPhone(rs.getString("phone"));
				mem.setUserId(rs.getString("user_id"));
				mem.setUserName(rs.getString("user_name"));
				list.add(mem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return list;
		
	}
	
	
	//조회
	public List<MemberVO> searchMemberList(MemberVO vo){
		connect();
		List<MemberVO> list = new ArrayList<>();
		String sql = "select * from member m\r\n"
				+ "where user_id = nvl(?,user_id)\r\n"
				+ "and nvl(user_name,'1') like '%'||?||'%'\r\n"
				+ "and nvl(address,'1') like '%'||?||'%'\r\n"
				+ "and nvl(phone,'1') like '%'||?||'%'";
		if(vo.getGender() != null && !vo.getGender().equals("all") && vo.getGender() != "") {
			sql += "and gender = ?";
		}
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getUserId());
			psmt.setString(2, vo.getUserName());
			psmt.setString(3, vo.getAddress());
			psmt.setString(4, vo.getPhone());
			if(vo.getGender() != null && !vo.getGender().equals("all") && vo.getGender() != "") {
				psmt.setString(5, vo.getGender());				
			}
			rs = psmt.executeQuery();
			while(rs.next()) {
				MemberVO mem = new MemberVO();
				mem.setAddress(rs.getString("address")); // DB 칼럼이름이랑 똑같이
				mem.setBirthDate(rs.getString("birth_date"));
				mem.setGender(rs.getString("gender"));
				mem.setPhone(rs.getString("phone"));
				mem.setUserId(rs.getString("user_id"));
				mem.setUserName(rs.getString("user_name"));
				list.add(mem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return list;
	}
	
	
	
	// 수정
	public Map<String, Object> updateMember(MemberVO vo) {
		// retCode:OK, retVal: vo
		// retCode:NG, retVal: errMsg
		String sql = "update member set user_name = ?, birth_date = ? , gender = ?, address = ?, phone = ? where user_id = ?";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("retCode", "NG");
		map.put("retVal", "Error!!!");

		connect();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getUserName());
			psmt.setString(2, vo.getBirthDate());
			psmt.setString(3, vo.getGender());
			psmt.setString(4, vo.getAddress());
			psmt.setString(5, vo.getPhone());
			psmt.setString(6, vo.getUserId());
			int r = psmt.executeUpdate();
			System.out.println(r + "건 수정");
			if (r > 0) {
				map.put("retCode", "OK");
				map.put("retVal", vo);
				return map;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			map.put("retVal", e.getMessage());
			return map;
		}finally {
			disconnect();
		}
		return map;
	}

	// 한건 삭제
	public boolean deleteMember(String id) {
		String sql = "delete from member where user_id = ?";
		connect();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			int r = psmt.executeUpdate();
			System.out.println(r + "건 삭제!");
			if (r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			disconnect();
		}
		return false;
	}

	// 한건 조회
	public MemberVO getMember(String id) {
		String sql = "select * from member where user_id = ?";
		connect();
		MemberVO vo = null;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if (rs.next()) {
				vo = new MemberVO();
				vo.setAddress(rs.getString("address")); // DB 칼럼이름이랑 똑같이
				vo.setBirthDate(rs.getString("birth_date"));
				vo.setGender(rs.getString("gender"));
				vo.setPhone(rs.getString("phone"));
				vo.setUserId(rs.getString("user_id"));
				vo.setUserName(rs.getString("user_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			disconnect();
		}
		return vo;
	}

	// 한건 입력
	public boolean insertMember(MemberVO vo) {
		String sql = "insert into member(user_id, user_name, address, phone, birth_date, gender) values(?,?,?,?,?,?)";
		connect();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getUserId());
			psmt.setString(2, vo.getUserName());
			psmt.setString(3, vo.getAddress());
			psmt.setString(4, vo.getPhone());
			psmt.setString(5, vo.getBirthDate());
			psmt.setString(6, vo.getGender());
			int r = psmt.executeUpdate();
			System.out.println(r + "건 입력됨.");
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			disconnect();
		}
		return true;

	}

	// 전체 리스트
	public List<MemberVO> getMemberList() {
		String sql = "select * from member order by 1";
		connect();
		List<MemberVO> memberList = new ArrayList<MemberVO>();
		try {
			stmt = conn.createStatement(); // Statement stmt = new Statement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setAddress(rs.getString("address")); // DB 칼럼이름이랑 똑같이
				vo.setBirthDate(rs.getString("birth_date"));
				vo.setGender(rs.getString("gender"));
				vo.setPhone(rs.getString("phone"));
				vo.setUserId(rs.getString("user_id"));
				vo.setUserName(rs.getString("user_name"));
				if(memberList.size() == 10) {
					break;
				}
				memberList.add(vo);
					
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return memberList;
	}
}
