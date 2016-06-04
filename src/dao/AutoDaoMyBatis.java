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
import exceptions.DAOException;

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
	
	public String getAutoartForId(int id) throws DAOException {
		SqlSession s = null;
		String art = null;
		try {
			s = ssf.openSession();
			art = s.selectOne("selectAutoArt", id);
		} catch(final org.apache.ibatis.exceptions.PersistenceException ex) {
			throw new DAOException("Fehler beim Abfragen der Autoart");
		}
		finally {
			s.close();
		}
		return art;
	}
	
	public List<AutoModell> getAllAutoModelle() throws DAOException {
		SqlSession s = null;
		List<AutoModell> ams;
		try {
			s = ssf.openSession();
			ams = s.selectList("selectAllAutoModell");
			
		} catch(final org.apache.ibatis.exceptions.PersistenceException ex) {
			throw new DAOException("Fehler beim Abfragen der aller Autos");
		} finally {
			s.close();
		}
		return ams;
	}
	
	
	public List<AutoModell> getFilteredModell(Map<String,String> map) throws DAOException {
		SqlSession s = null;
		List<AutoModell> ams;
		try {
			s = ssf.openSession();
			ams = s.selectList("selectAllFilteredAutoModell",map);
		} catch(final org.apache.ibatis.exceptions.PersistenceException ex) {
			throw new DAOException("Fehler beim Abfragen des Automodells");
		} finally {
			s.close();
		}
		return ams;
	}
	
	public List<Kunde> getAllKunde() throws DAOException {
		SqlSession s = null;
		List<Kunde> ams;
		try {
			s = ssf.openSession();
			ams = s.selectList("selectAllKunde");
		} catch(final org.apache.ibatis.exceptions.PersistenceException ex) {
			throw new DAOException("Fehler beim Abfragen der Kunden");
		} finally {
			s.close();
		}
		return ams;
	}
	
	public List<Auto> getFilteredAuto(int i) throws DAOException {
		SqlSession s = null;
		List<Auto> ams;
		try {
			s = ssf.openSession();
			ams = s.selectList("selectFilteredAuto",i);
		} catch(final org.apache.ibatis.exceptions.PersistenceException ex) {
			throw new DAOException("Fehler beim Abfragen des Autos");
		} finally {
			s.close();
		}
		return ams;
		
	}
	
	public List<Reservierung> getReservierung(int i) throws DAOException {
		SqlSession s = null;
		List<Reservierung> ams;
		try {
			s = ssf.openSession();
			ams = s.selectList("selectReservierung",i);
		} catch(final org.apache.ibatis.exceptions.PersistenceException ex) {
			throw new DAOException("Fehler beim Abfragen des Autos");
		} finally {
			s.close();
		}
		return ams;
	}
	
	public void insertReservierung(Reservierung res) throws DAOException {
		SqlSession s = ssf.openSession(TransactionIsolationLevel.SERIALIZABLE);
		try {
			s.getConnection().prepareStatement("LOCK TABLES Reservierung WRITE").execute();
			s.selectList("insertReservierung",res);
			s.getConnection().prepareStatement("UNLOCK TABLES").execute();
			s.commit();
		} catch (SQLException e) {
			throw new DAOException("Fehler beim Reservieren des Autos (LockTable)");
		} catch (final org.apache.ibatis.exceptions.PersistenceException ex) {
			throw new DAOException("Fehler beim Reservieren des Autos");			
		} finally{
			s.close();
		}
		
	}
}


