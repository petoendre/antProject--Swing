package hu.alkfejl.towncatalog.model;

import java.util.List;

import hu.alkfejl.towncatalog.model.bean.City;

public interface TownCatalogDAO {

	public boolean addCustomer(City city);

	public List<City> getCitys();

}
