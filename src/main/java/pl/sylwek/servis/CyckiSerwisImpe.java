package pl.sylwek.servis;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pl.sylwek.model.Cycki;

@Service
@EnableTransactionManagement
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CyckiSerwisImpe implements CyckiServis {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	@Transactional(readOnly = false)
	public Cycki save(Cycki cycki) {
		getCurrentSession().save(cycki);
		return cycki;
	}

	@Override
	public List<Cycki> findAll() {
		List<Cycki> list = getCurrentSession().createQuery("from Cycki").list();
		return list;
	}

}
