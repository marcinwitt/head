package org.mifos.application.personnel.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.mifos.application.NamedQueryConstants;
import org.mifos.application.customer.util.helpers.CustomerConstants;
import org.mifos.application.customer.util.helpers.Param;
import org.mifos.application.office.persistence.OfficePersistence;
import org.mifos.application.personnel.business.PersonnelBO;
import org.mifos.application.personnel.business.PersonnelView;
import org.mifos.application.personnel.util.helpers.PersonnelConstants;
import org.mifos.application.personnel.util.helpers.PersonnelLevel;
import org.mifos.framework.exceptions.HibernateProcessException;
import org.mifos.framework.exceptions.HibernateSearchException;
import org.mifos.framework.exceptions.PersistenceException;
import org.mifos.framework.hibernate.helper.HibernateUtil;
import org.mifos.framework.hibernate.helper.QueryFactory;
import org.mifos.framework.hibernate.helper.QueryInputs;
import org.mifos.framework.hibernate.helper.QueryResult;
import org.mifos.framework.persistence.Persistence;

public class PersonnelPersistence extends Persistence {

	public List<PersonnelView> getActiveLoanOfficersInBranch(Short levelId,
			Short officeId, Short userId, Short userLevelId)
			throws PersistenceException {
		HashMap<String, Object> queryParameters = new HashMap<String, Object>();
		queryParameters.put("levelId", levelId);
		queryParameters.put("userId", userId);
		queryParameters.put("userLevelId", userLevelId);
		queryParameters.put("officeId", officeId);
		queryParameters.put("statusId",
				CustomerConstants.LOAN_OFFICER_ACTIVE_STATE);
		List<PersonnelView> queryResult = executeNamedQuery(
				NamedQueryConstants.MASTERDATA_ACTIVE_LOANOFFICERS_INBRANCH,
				queryParameters);
		return queryResult;
	}

	public PersonnelBO getPersonnel(Short personnelId)
			throws PersistenceException {
		return (PersonnelBO) getPersistentObject(PersonnelBO.class, personnelId);
	}

	public boolean isUserExist(String userName) throws PersistenceException {

		HashMap<String, Object> queryParameters = new HashMap<String, Object>();
		queryParameters.put("USER_NAME", userName);
		Integer count = (Integer) execUniqueResultNamedQuery(
				NamedQueryConstants.GET_PERSONNEL_WITH_NAME, queryParameters);
		if (count != null) {
			return count > 0 ? true : false;
		}

		return false;
	}

	public boolean isUserExistWithGovernmentId(String governmentId)
			throws PersistenceException {

		HashMap<String, Object> queryParameters = new HashMap<String, Object>();
		queryParameters.put("GOVT_ID", governmentId);
		Integer count = (Integer) execUniqueResultNamedQuery(
				NamedQueryConstants.GET_PERSONNEL_WITH_GOVERNMENTID,
				queryParameters);
		if (count != null) {
			return count > 0 ? true : false;
		}
		return false;
	}

	public boolean isUserExist(String displayName, Date dob)
			throws PersistenceException {
		HashMap<String, Object> queryParameters = new HashMap<String, Object>();
		queryParameters.put("DISPLAY_NAME", displayName);
		queryParameters.put("DOB", dob);
		Integer count = (Integer) execUniqueResultNamedQuery(
				NamedQueryConstants.GET_PERSONNEL_WITH_DOB_AND_DISPLAYNAME,
				queryParameters);
		if (count != null) {
			return count > 0 ? true : false;
		}
		return false;
	}

	public boolean getActiveChildrenForLoanOfficer(Short personnelId,
			Short officeId) throws PersistenceException {
		HashMap<String, Object> queryParameters = new HashMap<String, Object>();
		queryParameters.put("userId", personnelId);
		queryParameters.put("officeId", officeId);
		Integer count = (Integer) execUniqueResultNamedQuery(
				NamedQueryConstants.GET_ACTIVE_CUSTOMERS_FOR_LO,
				queryParameters);
		if (count != null) {
			return count > 0 ? true : false;
		}
		return false;
	}

	public boolean getAllChildrenForLoanOfficer(Short personnelId,
			Short officeId) throws PersistenceException {
		HashMap<String, Object> queryParameters = new HashMap<String, Object>();
		queryParameters.put("userId", personnelId);
		queryParameters.put("officeId", officeId);
		Integer count = (Integer) execUniqueResultNamedQuery(
				NamedQueryConstants.GET_ALL_CUSTOMERS_FOR_LO, queryParameters);
		if (count != null) {
			return count > 0 ? true : false;
		}
		return false;
	}

	public PersonnelBO getPersonnelByGlobalPersonnelNum(
			String globalPersonnelNum) throws PersistenceException {
		HashMap<String, Object> queryParameters = new HashMap<String, Object>();
		queryParameters.put("globalPersonnelNum", globalPersonnelNum);

		PersonnelBO personnelBO = (PersonnelBO) execUniqueResultNamedQuery(
				NamedQueryConstants.PERSONNEL_BY_SYSTEM_ID, queryParameters);
		if (personnelBO != null) {
			return personnelBO;
		}
		return null;
	}

	public QueryResult getAllPersonnelNotes(Short personnelId)
			throws PersistenceException {
		QueryResult notesResult = null;
		try {
			Session session = null;
			notesResult = QueryFactory.getQueryResult("NotesSearch");
			session = notesResult.getSession();
			Query query = session
					.getNamedQuery(NamedQueryConstants.GETALLPERSONNELNOTES);
			query.setInteger("PERSONNEL_ID", personnelId);
			notesResult.executeQuery(query);
		} catch (HibernateProcessException hpe) {
			throw new PersistenceException(hpe);
		} catch (HibernateSearchException hse) {
			throw new PersistenceException(hse);
		}
		return notesResult;
	}

	public Integer getPersonnelRoleCount(Short roleId)
			throws PersistenceException {
		Map<String, Object> queryParameters = new HashMap<String, Object>();
		queryParameters.put("roleId", roleId);
		Integer count = (Integer) execUniqueResultNamedQuery(
				NamedQueryConstants.GET_PERSONNEL_ROLE_COUNT, queryParameters);
		return count;
	}

	public PersonnelBO getPersonnel(String personnelName)
			throws PersistenceException {
		PersonnelBO personnelBO = null;
		HashMap<String, Object> queryParameters = new HashMap<String, Object>();
		queryParameters.put("USER_NAME", personnelName);
		personnelBO = (PersonnelBO) execUniqueResultNamedQuery(
				NamedQueryConstants.GETPERSONNELBYNAME, queryParameters);
		return personnelBO;
	}

	public void updateWithCommit(PersonnelBO personnelBO)
			throws PersistenceException {
		super.createOrUpdate(personnelBO);
		try {
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			HibernateUtil.rollbackTransaction();
			throw new PersistenceException(e);
		}

	}

	public QueryResult search(String searchString, Short userId) throws PersistenceException {
		String[] namedQuery = new String[2];
		List<Param> paramList = new ArrayList<Param>();
		QueryInputs queryInputs = new QueryInputs();
		QueryResult queryResult = QueryFactory
				.getQueryResult(PersonnelConstants.USER_LIST);
		
		PersonnelBO personnel = new PersonnelPersistence().getPersonnel(userId);
		
		String officeSearchId = personnel.getOffice().getSearchId();
		namedQuery[0] = NamedQueryConstants.PERSONNEL_SEARCH_COUNT;
		namedQuery[1] = NamedQueryConstants.PERSONNEL_SEARCH;
		paramList.add(typeNameValue("String", "USER_NAME", searchString + "%"));
		paramList.add(typeNameValue("String", "SEARCH_ID", officeSearchId));
		paramList.add(typeNameValue("String", "SEARCH_ALL", officeSearchId
				+ ".%"));
		paramList.add(typeNameValue("Short", "USERID", userId));
		paramList.add(typeNameValue("Short", "LOID",
				PersonnelLevel.LOAN_OFFICER.getValue()));
		paramList.add(typeNameValue("Short", "USERLEVEL_ID",personnel.getLevel().getId()));
		String[] aliasNames = { "officeId", "officeName", "personnelId",
				"globalPersonnelNum", "personnelName" };
		queryInputs.setQueryStrings(namedQuery);
		queryInputs
				.setPath("org.mifos.application.personnel.util.helpers.UserSearchResultsView");
		queryInputs.setAliasNames(aliasNames);
		queryInputs.setParamList(paramList);
		try {
			queryResult.setQueryInputs(queryInputs);
		} catch (HibernateSearchException e) {
			throw new PersistenceException(e);
		}
		return queryResult;
	}
}
