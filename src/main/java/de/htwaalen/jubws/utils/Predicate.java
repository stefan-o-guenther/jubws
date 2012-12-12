package de.htwaalen.jubws.utils;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public abstract class Predicate<T> {
	
	public abstract boolean apply(T obj);
	
	
	public Predicate<T> and(final Predicate<T> other){
		return new Predicate<T>() {
			@Override
			public boolean apply(T obj) {
				return this.apply(obj) && other.apply(obj);
			}
		};
	}
	
	public Predicate<T> or(final Predicate<T> other){
		return new Predicate<T>() {
			@Override
			public boolean apply(T obj) {
				return this.apply(obj) || other.apply(obj);
			}
		};
	}
	
	public Predicate<T> not(){
		return new Predicate<T>() {
			@Override
			public boolean apply(T obj) {
				return !this.apply(obj);
			}
		};

	}
	
	public List<T> filter(Collection<T> items){
		List<T> result = new LinkedList<T>();
		for(T item : items){
			if(apply(item)){
				result.add(item);
			}
		}

		return result;
	}
}
