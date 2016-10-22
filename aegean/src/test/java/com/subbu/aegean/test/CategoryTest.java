package com.subbu.aegean.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.subbu.aegean.dao.CategoryDAO;
import com.subbu.aegean.model.Category;

public class CategoryTest {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		context.scan("com.subbu.aegean");
		context.refresh();

		CategoryDAO categoryDAO = (CategoryDAO) context.getBean("categoryDAO");
		Category category = (Category) context.getBean("category");
		category.setId("cat123");
		category.setName("electronics");
		
		categoryDAO.saveOrUpdate(category);

		if (categoryDAO.get("sdfsf") == null) {
			System.out.println("Category exist..the detials are..");
			System.out.println();
		}

	}

}