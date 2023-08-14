package com.jpa.demo.service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.jpa.demo.jpaRepo.JPARepository;
import com.jpa.demo.model.JPAModel;
import com.jpa.demo.model.Response;

@Service
public class JPAService {

	@PersistenceContext
	EntityManager entityManager;

	Response rsp = new Response();

	@Autowired
	JPARepository jparepo;

	public Response createUser(JPAModel values) {
		String uuid = UUID.randomUUID().toString().toString();
		values.setsNo(uuid);
		values.setCreatedBy(uuid);
		values.setUpdatedBy(uuid);

		Date date = new Date(Calendar.getInstance().getTime().getTime());
		values.setCreatedDate(date);
		values.setUpdatedDate(date);
		values.setIsActive(1);
		try {

			jparepo.save(values);
			rsp.setData("User Created Sucessfully");
			rsp.setResponseCode(200);
			rsp.setResponseMsg("success");

		} catch (Exception e) {
			e.printStackTrace();
			rsp.setData("User Cannot be created");
			rsp.setResponseCode(500);
			rsp.setResponseMsg("Error");

		}

		return rsp;
	}

	public Response updateUser(String sNo, String email) {
		try {
			Optional<JPAModel> optObj = jparepo.findById(sNo);

			if (optObj.isPresent()) {
				JPAModel update = optObj.get();
				update.setEmail(email);
				jparepo.save(update);
				rsp.setData("Email Updated Sucessfully");
				rsp.setResponseCode(200);
				rsp.setResponseMsg("success");
			}
		} catch (Exception e) {
			e.printStackTrace();

			rsp.setData("Email cannot be updated ");
			rsp.setResponseCode(200);
			rsp.setResponseMsg("success");
		}

		return rsp;
	}

	public Response deleteUser(String sNo) {
		try {
			Optional<JPAModel> optObj = jparepo.findById(sNo);

			if (optObj.isPresent()) {
				JPAModel del = optObj.get();

				jparepo.delete(del);
				rsp.setData("user can be deleted Sucessfully");
				rsp.setResponseCode(200);
				rsp.setResponseMsg("success");
			}
		} catch (Exception e) {

			e.printStackTrace();
			rsp.setData("User cannot be deleted ");
			rsp.setResponseCode(500);
			rsp.setResponseMsg("Error");
		}
		return rsp;
	}

	@SuppressWarnings("unchecked")
	public Response getallUser() {
		try {
			List<JPAModel> list = jparepo.findAll();
			JSONArray jsonArray = new JSONArray();
			for (JPAModel values : list) {

				JSONObject jsonObj = new JSONObject();
				jsonObj.put("sNO", values.getsNo());
				jsonObj.put("firstName", values.getFirstName());
				jsonObj.put("lastName", values.getLastName());
				jsonObj.put("email", values.getEmail());
				jsonObj.put("password", values.getPassword());
				jsonObj.put("gender", values.getGender());
				jsonObj.put("dob", values.getDob());

				jsonArray.add(jsonObj);
			}
			rsp.setjData(jsonArray);
			rsp.setData("get all user succesfully");
			rsp.setResponseCode(200);
			rsp.setResponseMsg("Success");

		} catch (Exception e) {

			e.printStackTrace();

			rsp.setData("not get data");
			rsp.setResponseCode(500);
			rsp.setResponseMsg("Error");

		}
		return rsp;
	}

	@GetMapping
	@SuppressWarnings("unchecked")
	public Response getOneUser(String sNo) {
		try {
			Optional<JPAModel> optObj = jparepo.findById(sNo);
			JSONObject jObj = new JSONObject();
			if (optObj.isPresent()) {

				JPAModel value = optObj.get();

				jObj.put("sNo", value.getsNo());
				jObj.put("firstName", value.getFirstName());
				jObj.put("lastName", value.getLastName());
				jObj.put("email", value.getEmail());
				jObj.put("password", value.getPassword());
				jObj.put("gender", value.getGender());
				jObj.put("phonenumber", value.getPhoneNumber());

			} else {

			}

			rsp.setjData(jObj);
			rsp.setData("get one user succesfully");
			rsp.setResponseCode(200);
			rsp.setResponseMsg("Success");

		} catch (Exception e) {

			e.printStackTrace();

			rsp.setData("not get data");
			rsp.setResponseCode(500);
			rsp.setResponseMsg("Error");

		}
		return rsp;
	}

	public Response ScamUser() {

		return rsp;
	}

	public Response loginUser(String email, String password) {
		try {
			Query query = entityManager
					.createQuery("SELECT u FROM JPAModel u WHERE u.email = :email AND u.password = :password");
			query.setParameter("email", email);
			query.setParameter("password", password);
			@SuppressWarnings("unchecked")
			List<JPAModel> value = query.getResultList();
			if (value.isEmpty()) {

				rsp.setData("No such user");
				rsp.setResponseCode(200);
				rsp.setResponseMsg("success");
			} else {
				rsp.setData("Existing user Login successfully");
				rsp.setResponseCode(500);
				rsp.setResponseMsg("success");
			}
		} catch (Exception e) {
			e.printStackTrace();
			rsp.setData("Login Failed");
			rsp.setResponseCode(500);
			rsp.setResponseMsg("Error");
		}
		return rsp;
	}

}
