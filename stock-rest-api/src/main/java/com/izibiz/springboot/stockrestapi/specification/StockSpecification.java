package com.izibiz.springboot.stockrestapi.specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import javax.persistence.criteria.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import com.izibiz.springboot.stockrestapi.model.Stock;

public class StockSpecification {

	public static Specification<Stock> predicate(Map<String, String> filters) {

		return (root, query, criteriaBuilder) -> {

			List<Predicate> predicates = new ArrayList<>();
			

			// sortfield null if eğer sortOrder =null defult asc

			if (filters.containsKey("sortField")) {
				if (Objects.nonNull(filters.get("sortField"))) {
					if (filters.containsKey("sortOrder")) {
						if ("ASCENDING".equals(filters.get("sortOrder").toString())) {
							query.orderBy(criteriaBuilder.asc(root.get("id")));
						} else if ("DESCENDING".equals(filters.get("sortOrder").toString())) {
							query.orderBy(criteriaBuilder.desc(root.get("id")));
						} else {
							query.orderBy(criteriaBuilder.asc(root.get("id")));

						}
					}
				}

			}

			filters.remove("sortField");
			filters.remove("first");
			filters.remove("pageSize");
			filters.remove("sortOrder");

			for (Entry<String, String> entry : filters.entrySet()) {
				predicates.add(criteriaBuilder.like((root.get(entry.getKey()).as(String.class)),
						"%" + entry.getValue().toString() + "%"));

			}

			return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));

		};

	}

}
