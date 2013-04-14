package com.gs.demo.hr.model.comverter.conversion;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.gs.demo.hr.common.annotations.CollectionFieldMapper;
import com.gs.demo.hr.common.annotations.Conversion;
import com.gs.demo.hr.common.annotations.FieldMapper;

/**
 * 
 * <p>		GenericConverter is for conversion from one type of object to a different type of object.
 * This conversion is only copy the data from one object to another type of object.
 * <br/>
 *  <b>Requirement:</b><br/>
 *  <code>&#64;Conversion -- </code>annotation is required to put at the class level of the source object. 
 *  The value of the <code>targetClassName</code> specifies the target object type.
 * <br/>
 * 	<code>&#64;FieldMapper -- </code>annotation should be provided at the getter method of the 
 * source object to copy the data from source field to specified target field.
 * <br/>
 * <b><i><u>Note:</u></i></b> Do not map self references.
 * <br/>
 *  <b><u>Usase:</u></b><br/>
 *  <code>
 *  
<pre>
&#64;Conversion(targetClassName="User")
public class UserVO{

	private String fName;
	private String lName;
	
	private UserVO supervisor;
	private Set<UserVO> childUsers;
	
	private Map<UserVO, UserVO> childMap;
	
	private List<String> values;
	
	public UserVO() {
		childUsers = new HashSet<UserVO>();
		values = new ArrayList<String>();
		childMap = new HashMap<UserVO, UserVO>();
	}

	&#64;FieldMapper(targetFieldName="firstName")
	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	&#64;FieldMapper(targetFieldName="lastName")
	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	&#64;FieldMapper(targetFieldName="supervisor", 
			targetConversion=&#64;Conversion(targetClassName="User")
	)
	public UserVO getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(UserVO supervisor) {
		this.supervisor = supervisor;
	}

	&#64;FieldMapper(
			targetFieldName="childUsers",
			targetConversion=&#64;Conversion(targetClassName="User"),
			collectionFieldMapper=&#64;CollectionFieldMapper(
					targetCollectionType="java.util.HashSet",
					addMethodName="add"
				)
		)
	public Set<UserVO> getChildUsers() {
		return childUsers;
	}

	public void setChildUsers(Set<UserVO> childUsers) {
		this.childUsers = childUsers;
	}

	&#64;FieldMapper(targetFieldName="valueList")
	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}

	&#64;FieldMapper(
			targetFieldName="childUserMap",
			targetConversion=&#64;Conversion(targetClassName="User"),
			collectionFieldMapper=&#64;CollectionFieldMapper(
					targetCollectionType="java.util.HashMap",
					targetKeyType="User",
					addMethodName="put"
				)
		)
	public Map<UserVO, UserVO> getChildMap() {
		return childMap;
	}

	public void setChildMap(Map<UserVO, UserVO> childMap) {
		this.childMap = childMap;
	}

	&#64;Override
	public String toString() {
		return "UserVO [fName=" + fName + ", lName=" + lName + "]";
	}
	
	 
	
}

public class User {

	private Long userId;
	private String userLogin;
	private String password;
	private String firstName;
	private String middleName;
	private String lastName;

	private User supervisor;
	private Set<User> childUsers;

	private List<String> valueList;
	
	private Map<User, User> childUserMap;
	
	public User() {
		childUsers = new HashSet<User>();
		valueList = new ArrayList<String>();
		childUserMap = new HashMap<User, User>();
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public User getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(User supervisor) {
		this.supervisor = supervisor;
	}

	public Set<User> getChildUsers() {
		return childUsers;
	}

	public void setChildUsers(Set<User> childUsers) {
		this.childUsers = childUsers;
	}

	
	public List<String> getValueList() {
		return valueList;
	}

	public void setValueList(List<String> valueList) {
		this.valueList = valueList;
	}

	public Map<User, User> getChildUserMap() {
		return childUserMap;
	}

	public void setChildUserMap(Map<User, User> childUserMap) {
		this.childUserMap = childUserMap;
	}

	&#64;Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + "]";
	}

	
}


	public static void main(String[] args) {
		UserVO vo = new UserVO();
		vo.setfName("Sabuj");
		vo.setlName("Das");
		
		UserVO supervisor = new UserVO();
		supervisor.setfName("Super");
		supervisor.setlName("User");
		
		vo.setSupervisor(supervisor);
		
		for (int i = 0; i < 5; i++) {
			UserVO voa = new UserVO();
			voa.setfName("Child");
			voa.setlName("_"+(i+1));
			vo.getChildUsers().add(voa);
			vo.getValues().add(voa.toString());
			vo.getChildMap().put(voa, voa);
		}
		
		try {
			User user = Converter.convert(vo);
			System.out.println(user);
			System.out.println(user.getSupervisor());
			for(User u : user.getChildUsers()){
				System.out.println(u);
			}
			for(String u : user.getValueList()){
				System.out.println(u);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}



</pre>
 *  
 *  </code>
 *  <br/>
 * </p>
 * @author Sabuj.das
 */

public class GenericConverter {

	public static <T, S> T convert(S source, Set<Integer> convertedHashs)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, ConversionNotSupported, 
			IllegalArgumentException, InvocationTargetException, 
			SecurityException, NoSuchMethodException {

		if (null == source)
			return null;

		if(null == convertedHashs)
			convertedHashs = new HashSet<Integer>();
		
		Class<S> sourceClass = (Class<S>) source.getClass();
		
		String targetClassName = "";
		Conversion conversion = source.getClass().getAnnotation(Conversion.class);
		if(null != conversion){
			targetClassName = conversion.targetClassName();
			if(null == targetClassName || "".equals(targetClassName)){
				throw new ConversionNotSupported("Target Class Name not found...");
			}
		} else {
			throw new ConversionNotSupported("Conversion Annotation not found...");
		}
		
		Class<T> targetClass = (Class<T>) Class.forName(targetClassName);
		
		T target = (T) targetClass.newInstance();

		if(null == target){
			throw new RuntimeException("Cannot initialize the target class : " + targetClassName);
		}
		
		Method[] targetMethods = targetClass.getMethods();
		Map<String, Method> targetSetterMethods = new HashMap<String, Method>();
		for (Method method : targetMethods) {
			if(method.getName().startsWith("set")){
				targetSetterMethods.put(method.getName(), method);
			}
		}
		
		Method[] sourceMethods = sourceClass.getMethods();
		if(null != sourceMethods && sourceMethods.length > 0){
			for (Method sourceMethod : sourceMethods) {
				if(sourceMethod.isAnnotationPresent(FieldMapper.class)){
					FieldMapper fieldMapper = sourceMethod.getAnnotation(FieldMapper.class);
					if(null != fieldMapper){
						Object value = sourceMethod.invoke(source, new Object[]{});
						String setterMethodName = "set" + StringUtils.capitalize(fieldMapper.targetFieldName());
						Method targetMethod = targetSetterMethods.get(setterMethodName);
						
						if(null == targetMethod){
							throw new NoSuchMethodException("Method Not Found : " + setterMethodName);
						}
						
						Conversion fieldConversion = fieldMapper.targetConversion();
						CollectionFieldMapper collectionFieldMapper = fieldMapper.collectionFieldMapper();
						
						if(!"NA".equals(fieldConversion.targetClassName())
								&& "NA".equals(collectionFieldMapper.targetCollectionType())){
							String targetTypeName = fieldConversion.targetClassName();
							Class<T> aggrigatedTargetClass = (Class<T>) Class.forName(targetClassName);
							
							T aggrigatedTarget = (T) aggrigatedTargetClass.newInstance();
							if(null == aggrigatedTarget){
								throw new RuntimeException("Cannot initialize the target class : " + targetTypeName);
							}
							if(!convertedHashs.contains(value.hashCode()))
								aggrigatedTarget = GenericConverter.<T, Object>convert(value, convertedHashs);
							
							targetMethod.invoke(target, aggrigatedTarget);
						} else if(!"NA".equals(fieldConversion.targetClassName())
								&& !"NA".equals(collectionFieldMapper.targetCollectionType())){ 
							String targetTypeName = fieldConversion.targetClassName();
							Class<T> aggrigatedTargetClass = (Class<T>) Class.forName(targetClassName);
							
							T aggrigatedTarget = (T) aggrigatedTargetClass.newInstance();
							if(null == aggrigatedTarget){
								throw new RuntimeException("Cannot initialize the target class : " + targetTypeName);
							}
							
							Class<T> targetCollectionClass = (Class<T>) Class.forName(collectionFieldMapper.targetCollectionType());
							T targetCollection = (T) targetCollectionClass.newInstance();
							
							if(null == targetCollection){
								throw new RuntimeException("Cannot initialize the target collection class : " + 
										collectionFieldMapper.targetCollectionType());
							}
							
							if(!"NA".equals(collectionFieldMapper.targetKeyType())){
								String targetKeyType = collectionFieldMapper.targetKeyType();
								Class<T> targetKeyClass = (Class<T>) Class.forName(targetKeyType);
								
								T targetKey = (T) aggrigatedTargetClass.newInstance();
								if(null == targetKey){
									throw new RuntimeException("Cannot initialize the target class : " + targetTypeName);
								}
								
								Map<S, S> values = (Map<S, S>) value;
								if(null != values){
									
									Set<S> keySet = values.keySet();
									
									Iterator<S> keyIterator = keySet.iterator();
									while(keyIterator.hasNext()){
										S sourceKey = keyIterator.next();
										if(convertedHashs.contains(sourceKey.hashCode()))
											continue;
										convertedHashs.add(sourceKey.hashCode());
										targetKey = GenericConverter.<T, S>convert(sourceKey, convertedHashs);
										S sourceValue = values.get(sourceKey);
										aggrigatedTarget = GenericConverter.<T, S>convert(sourceValue, convertedHashs);
										convertedHashs.add(aggrigatedTarget.hashCode());
										targetCollection.getClass().getMethod(collectionFieldMapper.addMethodName(), 
												Object.class, Object.class).invoke(targetCollection, targetKey, aggrigatedTarget);
									}
									
								}
								
							} else {
								
								Collection<T> values = (Collection<T>) value;
								if(null != values){
									Iterator<T> iterator = values.iterator();
									while(iterator.hasNext()){
										T t = iterator.next();
										if(convertedHashs.contains(t.hashCode()))
											continue;
										convertedHashs.add(t.hashCode());
										aggrigatedTarget = GenericConverter.<T, T>convert(t, convertedHashs);
										targetCollection.getClass().getMethod(collectionFieldMapper.addMethodName(), 
												Object.class).invoke(targetCollection, aggrigatedTarget);
									}
								}
							}
							
							targetMethod.invoke(target, targetCollection);
							
						} else if("NA".equals(fieldConversion.targetClassName())
								&& !"NA".equals(collectionFieldMapper.targetCollectionType())){ 
							
						}else{
							targetMethod.invoke(target, value);
						}
					}
				}
			}
		}
		
		convertedHashs.add(target.hashCode());
		return target;
	}

	
	
}
