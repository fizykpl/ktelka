package pl.sylwek.servis;

import java.util.List;

import pl.sylwek.model.Cycki;

public interface CyckiServis {
	
	public Cycki save(Cycki cycki);
	
	public List<Cycki> findAll();

}
