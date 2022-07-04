package com.remindermeapp.enums;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public enum AppRoles {
	
	USER(1, "User"),
	DEVELOPER(2, "Developer", USER),
	ADMIN(5, "Admin", DEVELOPER, USER)
	;
	private final String sval;
	private final int bitPosition;
	private final AppRoles [] includedRoles;
	
	private AppRoles(int pos, String sval, AppRoles...appRoles){
		this.bitPosition = pos;
		this.sval = sval;
		this.includedRoles = appRoles;
	}
	
	public long getBitPosition(){
		return bitPosition;
	}
	
	public static Set<AppRoles> getAppRolesSetByBits(long rolesBits){
		Set<AppRoles> roles = new HashSet<>();
		for(AppRoles role : AppRoles.values()) {
			if( (rolesBits & (1L<<role.getBitPosition()))  != 0){
				roles.add(role);
				if(role.includedRoles != null && role.includedRoles.length > 0) {
					for(AppRoles r: role.includedRoles) {
						roles.add(r);
					}
				}
			}
		}
		return roles;
	}
	
	public static List<AppRoles> getAppRolesListByBits(long rolesBits){
		List<AppRoles> roles = new LinkedList<>();
		for(AppRoles role : AppRoles.values()) {
			if( (rolesBits & (1L<<role.getBitPosition()))  != 0){
				roles.add(role);
				if(role.includedRoles != null && role.includedRoles.length > 0) {
					for(AppRoles r: role.includedRoles) {
						roles.add(r);
					}
				}
			}
		}
		return roles;
	}
	
	public static long getAppRolesBits(Set<AppRoles> roles){
		long rolesBit = 0;
		for(AppRoles r : roles) {
			rolesBit |= (1<<r.bitPosition);
		}
		return rolesBit;
	}
	
	public static long getAppRolesBits(List<AppRoles> appRoles){
		if (appRoles == null)
			return 0;
		return getAppRolesBits(AppRoles.getAppRolesSet(appRoles));
	}
	
	public static Set<AppRoles> getAppRolesSet(List<AppRoles> globalAppRoles) {
		Set<AppRoles> appRolesSet = new HashSet<>();
		for (AppRoles ar : globalAppRoles) {
			appRolesSet.add(ar);
		}
		return appRolesSet;
	}
	
	public String getName() {
		return name();
	}
	
	public String getSval() {
		return sval;
	}
	
	

}
