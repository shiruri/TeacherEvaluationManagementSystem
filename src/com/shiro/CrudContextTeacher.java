package com.shiro;

public class CrudContextTeacher {
	private CrudStrategyTeacher crudStrategy;
	
	 public CrudContextTeacher(CrudStrategyTeacher crudStrategy) {
	        this.crudStrategy = crudStrategy;
	    }
	 public void setCrudStrategy(CrudStrategyTeacher crudStrategy) {
	        this.crudStrategy = crudStrategy;
	    }
	    public void performCrud(Teacher teacher) throws Exception {
	    	crudStrategy.process(teacher);
	    }
	    	
		/*
		 * public class SortingContext { private SortingStrategy sortingStrategy;
		 * 
		 * public SortingContext(SortingStrategy sortingStrategy) { this.sortingStrategy
		 * = sortingStrategy; }
		 * 
		 * public void setSortingStrategy(SortingStrategy sortingStrategy) {
		 * this.sortingStrategy = sortingStrategy; }
		 * 
		 * public void performSort(int[] array) { sortingStrategy.sort(array); } }
		 * Strategy guide from geek4geeks
		 */ 
	  
}
