package code.DataBaseProject.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import code.DataBaseProject.models.Countries;

@Repository
public interface CountryRepository extends JpaRepository<Countries, Integer> {
	
	Optional<Countries> findByCountryNameEquals(String countryName);
	List<Countries> findByPopulationGreaterThanEqual(long populationRecord);
	List<Countries> findByCountryNameContainingOrderByCountryNameAsc(String countryName);
	List<Countries> findByStablishedDateBetween(LocalDateTime startDate, LocalDateTime endDate);
	List<Countries> findAllByStablishedDateBetween(Date start, Date end);

}
