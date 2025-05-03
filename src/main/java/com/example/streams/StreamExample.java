package com.example.streams;

import java.util.*;
import java.util.stream.Collectors;

public class StreamExample {

	static class Person {
		String name;
		int age;
		String gender;

		List<String> hobbies;

		Person(String name, int age, String gender, List<String> hobbies ) {
			this.name = name;
			this.age = age;
			this.gender = gender;
			this.hobbies = hobbies;
		}

		public String getName() {
			return name;
		}

		public int getAge() {
			return age;
		}

		public String getGender() {
			return gender;
		}

		public List<String> getHobbies() {
			return hobbies;
		}
	}
	
	public String concatListWithDelimiter(List<Long> orderIds) {
		
		String result = orderIds.stream().map(x -> x.toString()).collect(Collectors.joining(","));
		return result;
	}

	public static void main(String[] args) {
		String[] array = new String[] {"jaVa", "Spring", "Microservices", "java", "SqL", "Java", "cLoud", "spring"};
		Map<String, Long>  result = Arrays.stream(array).map(x -> x.toUpperCase()).collect(Collectors.groupingBy(s -> s, Collectors.counting()));
		System.out.println("Aggregated result " + result);

		List<Person> persons = Arrays.asList(new Person[] {
				 new Person("Abdul", 44, "Male", Arrays.asList(new String[] {"coding", "badminton"}))
				, new Person("Faridha", 40, "Female",Arrays.asList(new String[] {"cooking", "teaching"}))
				, new Person("Farzana", 8, "Male",Arrays.asList(new String[] {"drawing"}))
				, new Person("Farhaan", 14, "Female",Arrays.asList(new String[] {"football", "badminton"}))
				, new Person("Suhana", 12, "Female",Arrays.asList(new String[] {"singing", "drawing"}))
		});

		Map<String, List<Person>>  byGender =  persons.stream().collect(Collectors.groupingBy(Person::getGender));

		List<Person> sortedPersons = persons.stream().sorted( (x, y) -> x.age - y.age).collect(Collectors.toList());
		Person person = persons.stream().max(Comparator.comparingDouble(Person::getAge)).get();

		// get the second old person
		Person secondAged = persons.stream().sorted((a, b) -> b.age - a.age)
				.skip(1)
				.limit(1)
				.findFirst().orElse(null);

		System.out.println("Second aged person: " + secondAged.getName());

		List<Integer> salaries = Arrays.asList(5000, 8000, 6000, 8000, 9000, 9000, 10000);
		int secondLargestSal = salaries.stream().sorted((a, b) -> b -a)
				.skip(1)
				.limit(1)
				.findFirst().orElse(0);

		System.out.println("Second largest sal: " + secondLargestSal);

		persons.stream().flatMap(x -> x.hobbies.stream())
				.collect(Collectors.toList())
				.forEach(y -> {
					System.out.println(String.format("Name %s", y));
				});

		// duplicate elemets
		List<String> repeatStrs = List.of("foo", "bar", "foo", "bar");

		String dup = repeatStrs.stream().collect(Collectors.groupingBy(s -> s, Collectors.counting()))
				.entrySet().stream().max(Map.Entry.comparingByValue())
				.map(Map.Entry::getKey)
				.orElse(null);

		System.out.println("dup value " + dup);





	}

}
