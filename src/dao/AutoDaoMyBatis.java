package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.TransactionIsolationLevel;

import business.Auto;
import business.AutoModell;
import business.Kunde;
import business.Reservierung;

public class AutoDaoMyBatis {
	
	SqlSessionFactory ssf;
	
	public AutoDaoMyBatis() {
		SqlSessionFactoryBuilder sfb = new SqlSessionFactoryBuilder();
		String resource = "mybatis-config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			ssf = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getAutoartForId(int id) {
		SqlSession s = ssf.openSession();
		String art = s.selectOne("selectAutoArt", id);
		s.close();
		return art;
	}
	
	public List<AutoModell> getAllAutoModelle() {
		SqlSession s = ssf.openSession();
		List<AutoModell> ams = s.selectList("selectAllAutoModell");
		s.close();
		return ams;
	}
	
	
	public List<AutoModell> getFilteredModell(Map<String,String> map) {
		SqlSession s = ssf.openSession();
		List<AutoModell> ams = s.selectList("selectAllFilteredAutoModell",map);
		s.close();
		return ams;
	}
	
	public List<Kunde> getAllKunde() {
		SqlSession s = ssf.openSession();
		List<Kunde> ams = s.selectList("selectAllKunde");
		s.close();
		return ams;
	}
	
	public List<Auto> getFilteredAuto(int i) {
		SqlSession s = ssf.openSession();
		List<Auto> ams = s.selectList("selectFilteredAuto",i);
		s.close();
		return ams;
	}
	
	public List<Reservierung> getReservierung(int i) {
		SqlSession s = ssf.openSession();
		List<Reservierung> ams = s.selectList("selectReservierung",i);
		s.close();
		return ams;
	}
	
	public void insertReservierung(Reservierung res) {
		SqlSession s = ssf.openSession(TransactionIsolationLevel.SERIALIZABLE);
		try {
			s.getConnection().prepareStatement("LOCK TABLES Reservierung WRITE").execute();
			s.selectList("insertReservierung",res);
			s.getConnection().prepareStatement("UNLOCK TABLES").execute();
			s.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			s.close();
		}
		
	}
}
