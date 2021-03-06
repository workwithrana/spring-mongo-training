package com.rana.ecom.controller;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rana.ecom.constant.IEcomConstant;
import com.rana.ecom.dao.IEcomDao;
import com.rana.ecom.model.Product;
import com.rana.ecom.model.ServiceResponse;
import com.rana.ecom.model.UpdateProduct;



@Controller
@RequestMapping("/item")
public class EcomController {

	
	@Autowired
	IEcomDao ecomDao;
	
	
	
	@RequestMapping(value = "/getitembyid/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ServiceResponse getItemById(@PathVariable String id){
		ServiceResponse res = new ServiceResponse();
		try {
			Product product = ecomDao.getProductById(id);
			res.setObj(product);
			res.setStatus(IEcomConstant.SUCCESS);
		} catch (Exception e) {
			res.setStatus(IEcomConstant.FAILED);
			res.setMsg(e.getMessage());
		}
		return res;
	}
	
	@RequestMapping(value = "/getitembycat/{cat}/{skip}/{limit}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ServiceResponse getItemByCategory(@PathVariable String cat, @PathVariable int skip, @PathVariable int limit){
		ServiceResponse res = new ServiceResponse();
		try {
			List<Product> products = ecomDao.getProductByCategory(cat, skip, limit);
			res.setObj(products);
			res.setStatus(IEcomConstant.SUCCESS);
		} catch (Exception e) {
			res.setStatus(IEcomConstant.FAILED);
			res.setMsg(e.getMessage());
		}
		return res;
	}
	
	@RequestMapping(value = "/createproduct", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ServiceResponse createPost(@RequestBody Product product){
		ServiceResponse res = new ServiceResponse();
		
		product.setId(ObjectId.get().toString());
		product.setCreateDate(new Date(System.currentTimeMillis()));
		
		try {
			ecomDao.insertProduct(product);
			res.setStatus(IEcomConstant.SUCCESS);
		} catch (Exception e) {
			res.setStatus(IEcomConstant.FAILED);
			res.setMsg(e.getMessage());
		}
		
		return res;
	}
	
	
	@RequestMapping(value = "/updateproduct", method = RequestMethod.POST, consumes="application/json")
	public @ResponseBody ServiceResponse updatePost(@RequestBody UpdateProduct product){
		ServiceResponse res = new ServiceResponse();
		
		
		
		try {
			ecomDao.updateProductNew(product);
			res.setStatus(IEcomConstant.SUCCESS);
		} catch (Exception e) {
			res.setStatus(IEcomConstant.FAILED);
			res.setMsg(e.getMessage());
		}
		
		return res;
	}
	
	@RequestMapping(value = "/updateprice", method = RequestMethod.POST, consumes="application/json")
	public @ResponseBody ServiceResponse updatePrice(@RequestBody Product product){
		ServiceResponse res = new ServiceResponse();
		
		
		
		try {
			ecomDao.updatePrice(product);
			res.setStatus(IEcomConstant.SUCCESS);
		} catch (Exception e) {
			res.setStatus(IEcomConstant.FAILED);
			res.setMsg(e.getMessage());
		}
		
		return res;
	}
	
	@RequestMapping(value = "/updateinventory", method = RequestMethod.POST, consumes="application/json")
	public @ResponseBody ServiceResponse updateInventory(@RequestBody Product product){
		ServiceResponse res = new ServiceResponse();
		
		
		
		try {
			ecomDao.updateInventory(product);
			res.setStatus(IEcomConstant.SUCCESS);
		} catch (Exception e) {
			res.setStatus(IEcomConstant.FAILED);
			res.setMsg(e.getMessage());
		}
		
		return res;
	}
	
	
	
	
	/**
	 * Get product based on category with create date descending - Punit's Team
	 * @param cat
	 * @param skip
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/getproducts/{cat}/{skip}/{limit}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ServiceResponse getproductsByCategory(@PathVariable String cat, @PathVariable int skip, @PathVariable int limit){
		ServiceResponse res = new ServiceResponse();
		try {
			List<Product> products = ecomDao.getProductsByCategory(cat, skip, limit);
			res.setObj(products);
			res.setStatus(IEcomConstant.SUCCESS);
		} catch (Exception e) {
			res.setStatus(IEcomConstant.FAILED);
			res.setMsg(e.getMessage());
		}
		return res;
	}
	
	
	

}
