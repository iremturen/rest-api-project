package com.izibiz.springboot.stockrestapi.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.izibiz.springboot.stockrestapi.dao.StockDao;
import com.izibiz.springboot.stockrestapi.exception.StockNotFoundException;
import com.izibiz.springboot.stockrestapi.model.Stock;
import com.izibiz.springboot.stockrestapi.specification.StockSpecification;

@Service
@Transactional(rollbackFor = Exception.class)
public class StockServiceImpl implements StockService {

	private StockDao stockDao;

	@Autowired
	public void setStockDao(StockDao stockDao) {
		this.stockDao = stockDao;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Stock> findStocks() {
		return stockDao.findAll();
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Stock findStockById(Long id) throws StockNotFoundException {
		Optional<Stock> stock = stockDao.findById(id);
		if (!(stock.isPresent()))
			throw new StockNotFoundException("Stock not found with id: " + id);
		return stock.get();

	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Page<Stock> findStocksWithFilter(Map<String, String> filters) {

		Sort sort = Sort.by(filters.get("sortField").toString());
		sort = filters.get("sortOrder").equals("ASCENDING") ? sort.ascending() : sort.descending();
		
		Pageable page = PageRequest.of(Integer.valueOf(filters.get("first").toString()),
				Integer.valueOf(filters.get("pageSize").toString()), sort);

		Page<Stock> pagedResult = stockDao.findAll(StockSpecification.predicate(filters), page);
		
		return pagedResult;

	}

	@Override
	public Stock createStock(Stock stock) {
		return stockDao.save(stock);
	}

	@Override
	public Stock update(Stock stock) {
		Stock stk = stockDao.findById(stock.getId()).get();
		stk.setBrand(stock.getBrand());
		stk.setEmail(stock.getEmail());
		stk.setId(stock.getId());
		stk.setInventoryId(stock.getInventoryId());
		stk.setName(stock.getName());
		stk.setQuantity(stock.getQuantity());
		stk.setUnitPrice(stock.getUnitPrice());
		return stockDao.save(stk);
	}

	@Override
	public void deleteStock(Long id) {
		stockDao.deleteById(id);
		
	}


	

}
