package com.texas.anexus.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.texas.anexus.exceptions.AlreadyExistException;
import com.texas.anexus.exceptions.NotFoundException;
import com.texas.anexus.model.District;
import com.texas.anexus.model.Metropolitan;
import com.texas.anexus.model.Municipality;
import com.texas.anexus.model.RuralMunicipality;
import com.texas.anexus.model.State;
import com.texas.anexus.model.SubMetropolitan;
import com.texas.anexus.repository.DistrictRepository;
import com.texas.anexus.repository.MetropolitanRepository;
import com.texas.anexus.repository.MunicipalityRepository;
import com.texas.anexus.repository.RuralMunicipalityRepository;
import com.texas.anexus.repository.StateRepository;
import com.texas.anexus.repository.SubMetropolitanRepository;
import com.texas.anexus.response.DistrictResponseDto;
import com.texas.anexus.response.PalikaResponseDto;
import com.texas.anexus.response.StateResponseDto;
import com.texas.anexus.services.CommonService;
import com.texas.anexus.util.LocalLevelType;
import com.texas.anexus.util.Status;

import io.swagger.annotations.ApiOperation;

/*
 * <<Here is the list of API to post state, district, rural municipality, municipality, metropolitan, subMetropolitan>>*/

@RestController
@RequestMapping("api/v1/addresses")
public class AddressController {

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private DistrictRepository districtRepository;

	@Autowired
	private CommonService commonService;

	@Autowired
	private RuralMunicipalityRepository ruralMunicipalityRepository;

	@Autowired
	private MunicipalityRepository municipalityRepository;

	@Autowired
	private SubMetropolitanRepository subMetropolitanRepository;
	
	@Autowired
	private MetropolitanRepository metropolitanRepository;

	/* Api to post state from excel file */
	@SuppressWarnings({ "rawtypes", "resource" })
	@ApiOperation(value = "Upload an excel file for State")
	@RequestMapping(value = "/uploadState", method = RequestMethod.POST)
	ResponseEntity<Object> processExcelSheet(@RequestParam("state") MultipartFile multipartFile) throws IOException {

		InputStream stream = multipartFile.getInputStream();
		XSSFWorkbook workbook = new XSSFWorkbook(stream);
		XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFRow row;
		Iterator rows = sheet.rowIterator();

		State st = stateRepository.findByState("state 7");
		if (st != null) {

			throw new AlreadyExistException("State file has been already uploaded into the database.");
		}

		while (rows.hasNext()) {
			row = (XSSFRow) rows.next();
			if (row.getRowNum() == 0) {
				continue;
			}

			try {

				State state = new State();
				String string0 = row.getCell(1).toString();
				byte[] u0 = string0.getBytes("UTF-8");
				string0 = new String(u0, "UTF-8");
				state.setState(string0);

				state.setStatus(Status.ACTIVE);

				stateRepository.save(state);
			}

			catch (UnsupportedEncodingException e) {
			}

		}

		return new ResponseEntity<Object>("State uploaded!", HttpStatus.OK);
	}

	/*
	 * <<This is the API to upload excel file for Districts>>
	 */
	@SuppressWarnings({ "rawtypes", "resource" })
	@ApiOperation(value = "Upload an excel file for District")
	@RequestMapping(value = "/uploadDistrict", method = RequestMethod.POST)
	ResponseEntity<Object> processExcelSheetForDistrict(@RequestParam("district") MultipartFile multipartFile)
			throws IOException {

		InputStream stream = multipartFile.getInputStream();
		XSSFWorkbook workbook = new XSSFWorkbook(stream);
		XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFRow row;
		Iterator rows = sheet.rowIterator();

		District dist = districtRepository.findByDistrict("Jhapa");
		if (dist != null) {

			throw new AlreadyExistException("District file has been already uploaded into the database.");
		}

		while (rows.hasNext()) {
			row = (XSSFRow) rows.next();
			if (row.getRowNum() == 0) {
				continue;
			}

			try {

				District district = new District();
				String string0 = row.getCell(1).toString();
				byte[] u0 = string0.getBytes("UTF-8");
				string0 = new String(u0, "UTF-8");
				district.setDistrict(string0);

				String string01 = row.getCell(2).toString();
				byte[] u01 = string01.getBytes("UTF-8");
				string01 = new String(u01, "UTF-8");
				State state = stateRepository.findByState(string01);
				if (state == null) {
					throw new NotFoundException("State with name " + string01 + " couldn't be found!");
				}
				district.setState(state);

				district.setStatus(Status.ACTIVE);

				districtRepository.save(district);
			}

			catch (UnsupportedEncodingException e) {
			}

		}

		return new ResponseEntity<Object>("District uploaded", HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "resource" })
	@ApiOperation(value = "Upload an excel file for Municipality")
	@RequestMapping(value = "/uploadMunicipality", method = RequestMethod.POST)
	ResponseEntity<Object> processExcelSheetForMunicipality(@RequestParam("municipality") MultipartFile multipartFile)
			throws IOException {

		InputStream stream = multipartFile.getInputStream();
		XSSFWorkbook workbook = new XSSFWorkbook(stream);
		XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFRow row;
		Iterator rows = sheet.rowIterator();

		while (rows.hasNext()) {
			row = (XSSFRow) rows.next();
			if (row.getRowNum() == 0) {
				continue;
			}

			try {

				Municipality municipality = new Municipality();
				String string0 = row.getCell(2).toString();
				byte[] u0 = string0.getBytes("UTF-8");
				string0 = new String(u0, "UTF-8");
				municipality.setMunicipality(string0);

				String string01 = row.getCell(1).toString();
				byte[] u01 = string01.getBytes("UTF-8");
				string01 = new String(u01, "UTF-8");
				District district = districtRepository.findByDistrict(string01);
				if (district == null) {
					throw new NotFoundException("District with name " + string01 + " couldn't be found!");
				}
				municipality.setDistrict(district);

				municipality.setStatus(Status.ACTIVE);
				municipalityRepository.save(municipality);
			}

			catch (UnsupportedEncodingException e) {
			}

		}

		return new ResponseEntity<Object>("Municipality uploaded!", HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "resource" })
	@ApiOperation(value = "Upload an excel file for Rural Municipality")
	@RequestMapping(value = "/uploadRuralMunicipality", method = RequestMethod.POST)
	ResponseEntity<Object> processExcelSheetForRuralMunicipality(
			@RequestParam("ruralMunicipality") MultipartFile multipartFile) throws IOException {

		InputStream stream = multipartFile.getInputStream();
		XSSFWorkbook workbook = new XSSFWorkbook(stream);
		XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFRow row;
		Iterator rows = sheet.rowIterator();

		RuralMunicipality rm = ruralMunicipalityRepository.findByRuralMunicipality("Aamchok");
		if (rm != null) {
			throw new AlreadyExistException("Rural Municipality File has been already uploaded into database.");
		}

		while (rows.hasNext()) {
			row = (XSSFRow) rows.next();
			if (row.getRowNum() == 0) {
				continue;
			}

			try {

				RuralMunicipality ruralMunicipality = new RuralMunicipality();
				String string0 = row.getCell(2).toString();
				byte[] u0 = string0.getBytes("UTF-8");
				string0 = new String(u0, "UTF-8");
				ruralMunicipality.setRuralMunicipality(string0);

				String string01 = row.getCell(1).toString();
				byte[] u01 = string01.getBytes("UTF-8");
				string01 = new String(u01, "UTF-8");
				District district = districtRepository.findByDistrict(string01);
				if (district == null) {
					throw new NotFoundException("District with name " + string01 + " couldn't be found!");
				}
				ruralMunicipality.setDistrict(district);

				ruralMunicipality.setStatus(Status.ACTIVE);

				ruralMunicipalityRepository.save(ruralMunicipality);
			}

			catch (UnsupportedEncodingException e) {
			}

		}
		return new ResponseEntity<Object>("RuralMunicipality file uploaded", HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "resource" })
	@ApiOperation(value = "Upload an excel file for Sub-Metropolitan")
	@RequestMapping(value = "/uploadSubMetropolitan", method = RequestMethod.POST)
	ResponseEntity<Object> processExcelSheetForSubMetropolitan(
			@RequestParam("subMetropolitan") MultipartFile multipartFile) throws IOException {

		InputStream stream = multipartFile.getInputStream();
		XSSFWorkbook workbook = new XSSFWorkbook(stream);
		XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFRow row;
		Iterator rows = sheet.rowIterator();

		SubMetropolitan m = subMetropolitanRepository.findBySubMetropolitan("Dharan");
		if (m != null) {

			throw new AlreadyExistException("Sub-Metropolitan file has been already uploaded into the database.");
		}

		while (rows.hasNext()) {
			row = (XSSFRow) rows.next();
			if (row.getRowNum() == 0) {
				continue;
			}

			try {

				SubMetropolitan subMetropolitan = new SubMetropolitan();
				String string0 = row.getCell(2).toString();
				byte[] u0 = string0.getBytes("UTF-8");
				string0 = new String(u0, "UTF-8");
				subMetropolitan.setSubMetropolitan(string0);

				String string01 = row.getCell(1).toString();
				byte[] u01 = string01.getBytes("UTF-8");
				string01 = new String(u01, "UTF-8");
				District district = districtRepository.findByDistrict(string01);
				if (district == null) {
					throw new NotFoundException("District with name " + string01 + " couldn't be found!");
				}
				subMetropolitan.setDistrict(district);

				subMetropolitan.setStatus(Status.ACTIVE);

				subMetropolitanRepository.save(subMetropolitan);
			}

			catch (UnsupportedEncodingException e) {
			}

		}

		return new ResponseEntity<Object>("Sub Metropolitan uploaded!", HttpStatus.OK);
	}
	
	@SuppressWarnings({ "rawtypes", "resource" })
	@ApiOperation(value = "Upload an excel file for Metropolitan")
	@RequestMapping(value = "/uploadMetropolitan", method = RequestMethod.POST)
	ResponseEntity<Object> processExcelSheetForMetropolitan(@RequestParam("Metropolitian") MultipartFile multipartFile)
			throws IOException {

		InputStream stream = multipartFile.getInputStream();
		XSSFWorkbook workbook = new XSSFWorkbook(stream);
		XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFRow row;
		Iterator rows = sheet.rowIterator();

		
		Metropolitan m=metropolitanRepository.findByMetropolitan("Birgunj");
		if(m!=null) {
			
			throw new AlreadyExistException("Metropolitan file has been already uploaded into the database.");
		}
		
		while (rows.hasNext()) {
			row = (XSSFRow) rows.next();
			if (row.getRowNum() == 0) {
				continue;
			}

			try {
				

				Metropolitan metropolitan = new Metropolitan();
				String string0 = row.getCell(2).toString();
				byte[] u0 = string0.getBytes("UTF-8");
				string0 = new String(u0, "UTF-8");
				metropolitan.setMetropolitan(string0);

				String string01 = row.getCell(1).toString();
				byte[] u01 = string01.getBytes("UTF-8");
				string01 = new String(u01, "UTF-8");
				District district=districtRepository.findByDistrict(string01);
				if(district==null) {
					throw new NotFoundException("District with name "+string01+" couldn't be found!");
				}
				metropolitan.setDistrict(district);
				
				
				metropolitan.setStatus(Status.ACTIVE);

				metropolitanRepository.save(metropolitan);
			}

			catch (UnsupportedEncodingException e) {
			}

		}

		return new ResponseEntity<Object>("Metropolitan Uploaded!",HttpStatus.OK);
	}

	/* API to get all state name */
	@ApiOperation(value = "Get all state name")
	@RequestMapping(value = "states", method = RequestMethod.GET)
	public ResponseEntity<Object> getAllStates() {

		List<StateResponseDto> srd = commonService.getAllStates();
		return new ResponseEntity<Object>(srd, HttpStatus.OK);

	}

	@ApiOperation(value = "List districts from state")
	@RequestMapping(value = "state/{state:.+}", method = RequestMethod.GET)
	public ResponseEntity<Object> listAllDistricts(@PathVariable("state") String state) {
		List<DistrictResponseDto> districtDtoList = commonService.listAllDistricts(state);
		return new ResponseEntity<Object>(districtDtoList, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Show all the local level belonging to the particular district")
	@RequestMapping(value = "localLevelList/{district:.+}", method = RequestMethod.GET)
	public ResponseEntity<Object> getLocalLevelFromDisrict(@PathVariable String district) {

		List<PalikaResponseDto> prd=commonService.getPalikaList(district);
		// List<LocalLevelResponseDto>
		// localLevelResponseDto=districtService.getLocalLevel(district);
		return new ResponseEntity<Object>(prd, HttpStatus.OK);

	}

}
