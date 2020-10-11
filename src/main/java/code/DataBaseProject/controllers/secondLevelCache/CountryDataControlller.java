package code.DataBaseProject.controllers.secondLevelCache;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import code.DataBaseProject.Exception.FunctionalException;
import code.DataBaseProject.Repository.CountryRepository;
import code.DataBaseProject.Response.SuccessRestResponse;
import code.DataBaseProject.models.Countries;

@RestController
@RequestMapping(value = "/country")
public class CountryDataControlller {

	private static Logger logger = LoggerFactory.getLogger(CountryDataControlller.class);

	@Autowired

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private CountryRepository repository;

	/**
	 * @param countries
	 * @return
	 * @throws FunctionalException
	 */
	@Transactional
	@RequestMapping(method = RequestMethod.POST, value = "/save/data", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<SuccessRestResponse> saveCountryData(@Valid @RequestBody Countries countries)
			throws FunctionalException {

		SuccessRestResponse successRestResponse = new SuccessRestResponse();
		em.persist(countries);
		successRestResponse.setDate(LocalDateTime.now());
		successRestResponse.setSuccess(true);
		successRestResponse.setMessage("Country Data Saved Successfuly");
		return new ResponseEntity<SuccessRestResponse>(successRestResponse, HttpStatus.OK);
	}

	/**
	 * @param id
	 * @return
	 * @throws FunctionalException
	 */
	@Transactional
	@RequestMapping(method = RequestMethod.GET, value = "/get/data/{id}")
	public ResponseEntity<SuccessRestResponse> getCountryDataById(@PathVariable("id") int id)
			throws FunctionalException {

		SuccessRestResponse successRestResponse = new SuccessRestResponse();
		Optional<Countries> countrydata = repository.findById(id);
		successRestResponse.setData(countrydata);
		successRestResponse.setDate(LocalDateTime.now());
		successRestResponse.setSuccess(true);
		return new ResponseEntity<SuccessRestResponse>(successRestResponse, HttpStatus.OK);
	}
	
	@Transactional
	@RequestMapping(method = RequestMethod.GET, value = "/get/data")
	public ResponseEntity<SuccessRestResponse> getCountryDataByName(@RequestParam String countryName)
			throws FunctionalException {

		SuccessRestResponse successRestResponse = new SuccessRestResponse();
		Optional<Countries> countrydata = repository.findByCountryNameEquals(countryName);
		successRestResponse.setData(countrydata);
		successRestResponse.setDate(LocalDateTime.now());
		successRestResponse.setSuccess(true);
		return new ResponseEntity<SuccessRestResponse>(successRestResponse, HttpStatus.OK);
	}
	
	@Transactional
	@RequestMapping(method = RequestMethod.GET, value = "/get/dataByPopuation")
	public ResponseEntity<SuccessRestResponse> getCountryDataByPopulation(@RequestParam long populationSize)
			throws FunctionalException {

		SuccessRestResponse successRestResponse = new SuccessRestResponse();
		List<Countries> countrydata = repository.findByPopulationGreaterThanEqual(populationSize);
		successRestResponse.setData(countrydata);
		successRestResponse.setDate(LocalDateTime.now());
		successRestResponse.setSuccess(true);
		return new ResponseEntity<SuccessRestResponse>(successRestResponse, HttpStatus.OK);
	}
	
	@Transactional
	@RequestMapping(method = RequestMethod.GET, value = "/get/dataBySimilarNames")
	public ResponseEntity<SuccessRestResponse> getCountryDataByNameLike(@RequestParam String countryName)
			throws FunctionalException {

		SuccessRestResponse successRestResponse = new SuccessRestResponse();
		List<Countries> countrydata = repository.findByCountryNameContainingOrderByCountryNameAsc(countryName);
		successRestResponse.setData(countrydata);
		successRestResponse.setDate(LocalDateTime.now());
		successRestResponse.setSuccess(true);
		return new ResponseEntity<SuccessRestResponse>(successRestResponse, HttpStatus.OK);
	}
	
	@Transactional
	@RequestMapping(method = RequestMethod.GET, value = "/get/dataBetween/EstablishedDates")
	public ResponseEntity<SuccessRestResponse> getCountryDataByEstablishedDates(@RequestParam String startDate,@RequestParam String endDate)
			throws FunctionalException, ParseException {

		SuccessRestResponse successRestResponse = new SuccessRestResponse();
		DateFormat format = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
		Date start=format.parse(startDate);
		Date end=format.parse(startDate);
		List<Countries> countrydata = repository.findAllByStablishedDateBetween(start,end);
		successRestResponse.setData(countrydata);
		successRestResponse.setDate(LocalDateTime.now());
		successRestResponse.setSuccess(true);
		return new ResponseEntity<SuccessRestResponse>(successRestResponse, HttpStatus.OK);
	}

}
