/* ******************************************************************************
 * 	
 * 	Name	: PackageManager.java
 * 	Type	: com.gs.automill.model.PackageManager
 * 
 * 	Created	: May 5, 2012
 * 	
 * 	Author	: Sabuj Das [ mailto::sabuj.das@gmail.com ]
 * 
 * -----------------------------------------------------------------------------*
 * 																				*
 * Copyright © Sabuj Das 2010 All Rights Reserved. 								*
 * <br/>No part of this document may be reproduced without written 				*
 * consent from the author.														*
 * 																				*
 ****************************************************************************** */

package com.gs.automill.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.gs.utils.collection.*;
/**
 * @author sabuj.das
 * @MailTo sabuj.das@gmail.com
 * 
 */
public final class PackageManager implements Cloneable{

	private Map<String, Set<Model>> jarPackageMap;
	
	private static PackageManager instance;
	private PackageManager(){
		jarPackageMap = new HashMap<String, Set<Model>>();
	}
	
	
	public static PackageManager getInstance() {
		synchronized (PackageManager.class) {
			if(null == instance)
				instance = new PackageManager();
		}
		return instance;
	}
	
	public void resetModels(){
		synchronized (PackageManager.class) {
			jarPackageMap = new HashMap<String, Set<Model>>();
		}
	}

	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException("Singleton PackageManager instance cannot be cloned !!!");
	}
	
	public Set<Model> getPackages(String jarName){
		return jarPackageMap.get(jarName);
	}
	
	public void loadJar(String jarName) throws IOException{
		ZipInputStream inputStream = new ZipInputStream(new FileInputStream(new File(jarName)));
		if(null != inputStream){
			ZipEntry entry = null;
			while((entry = inputStream.getNextEntry()) != null){
				if(!entry.getName().startsWith("META-INF")){
					if(entry.getName().endsWith("/")){
						addPackage(jarName, 
								entry.getName().substring(0, entry.getName().length()-1));
					} else {
						addPackage(jarName, entry.getName());
					}
				}
			}
		}
		
	}
	
	public Model getPackageRoot(String jarName, String rootPakName){
		if(null != jarPackageMap.get(jarName)){
			Set<Model> rootModels = jarPackageMap.get(jarName);
			for (Model model : rootModels) {
				if(rootPakName.equals(model.getName())){
					return model;
				}
			}
		}
		return null;
	}
	
	private Model[] createPackPath(String ... pathItems){
		
		if(null != pathItems && pathItems.length > 0){
			Model[] packPath = new Model[pathItems.length];
			for (int i = 0; i < pathItems.length; i++) {
				String path = pathItems[i];
				Model pak = new Model(path);
				if(i != 0){
					pak.setParent(packPath[i-1]);
				}
				packPath[i] = pak;
			}
			return packPath;
		}
		return null;
	}
	
	public void addPackage(String jarName, String name){
		if(null == jarPackageMap.get(jarName)){
			jarPackageMap.put(jarName, new LinkedHashSet<Model>());
		}
		if(name.contains(Model.PACKAGE_SEP)){
			String []pathItems = name.split(Model.PACKAGE_SEP);
			if(pathItems.length > 1){
				Model[] packPath = createPackPath(pathItems);
				Model rootModel = getPackageRoot(jarName, pathItems[0]);
				if(null != rootModel && null != packPath){
					try {
						addLastModel(rootModel,
								packPath[packPath.length-1],
								Arrays.copyOfRange(packPath, 0, packPath.length-1)
								);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
		} else {
			Model rootModel = new Model(name);
			rootModel.setPackage(true);
			jarPackageMap.get(jarName).add(rootModel);
		}
		
	}
	
	/**
	 * @param model
	 * @param copyOfRange
	 * @throws Exception 
	 */
	public void addLastModel(final Model rootModel, Model lastModel, Model[] pathItems) throws Exception {
		if(null != pathItems && pathItems.length > 0){
			if(!rootModel.equals(pathItems[0])){
				throw new Exception("Invalid root item");
			}
			Model head = findLastModelInPath(rootModel, pathItems);
			if(null != head){
				lastModel.setParent(head);
				head.getChildren().add(lastModel);
				return ;
			}
			
		}
		return ;
	}
	
	
	public Model findLastModelInPath(final Model rootModel, Model ... pathItems ){
		if(null != pathItems && pathItems.length > 0){
			Model head = rootModel;
			if(!head.equals(pathItems[0]))
				return null;
			for (int i = 1; i < pathItems.length; i++) {
				Set<Model> children = head.getChildren();
				for (Model node : children) {
					if(node.equals(pathItems[i])){
						head = node;
						continue;
					}
				}
			}
			return head;
		}
		return null;
	}
	
	
	public Set<Model> getClassModels(String jarName){
		Set<Model> rootModels = jarPackageMap.get(jarName);
		Set<Model> classModels = new HashSet<Model>();
		if(CollectionUtils.hasElements(rootModels)){
			for (Model model : rootModels) {
				model.listPackages(model, classModels, false);
			}
		}
		return classModels;
	}
	
}
