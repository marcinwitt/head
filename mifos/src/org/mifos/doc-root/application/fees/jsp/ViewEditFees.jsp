<!--
/**

* ViewEditFees.jsp    version: 1.0



* Copyright (c) 2005-2006 Grameen Foundation USA

* 1029 Vermont Avenue, NW, Suite 400, Washington DC 20005

* All rights reserved.



* Apache License
* Copyright (c) 2005-2006 Grameen Foundation USA
*

* Licensed under the Apache License, Version 2.0 (the "License"); you may
* not use this file except in compliance with the License. You may obtain
* a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
*

* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and limitations under the

* License.
*
* See also http://www.apache.org/licenses/LICENSE-2.0.html for an explanation of the license

* and how it is applied.

*

*/
 -->
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/tags/mifos-html" prefix="mifos"%>
<%@taglib uri="http://struts.apache.org/tags-html-el" prefix="html-el"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<tiles:insert definition=".view">
	<tiles:put name="body" type="string">
		<script>
				function fnOnAdmin(form){
					form.method.value="load";
					form.action="AdminAction.do";
					form.submit();
				}
				function fnOnNewFee(form){
					form.method.value="load";
					form.action="feeaction.do";
					form.submit();
				}
				function fnOnView(Id){
					document.feeactionform.feeId.value=Id;
					document.feeactionform.method.value="get";
					document.feeactionform.action="feeaction.do";
					document.feeactionform.submit();
				}
			</script>
		<html-el:form action="/feeaction.do">
			<table width="95%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td class="bluetablehead05">
						<span class="fontnormal8pt"> <html-el:link href="javascript:fnOnAdmin(feeactionform)">
								<mifos:mifoslabel name="Fees.admin" bundle="FeesUIResources">
								</mifos:mifoslabel>
							</html-el:link> / </span> <span class="fontnormal8ptbold"> <mifos:mifoslabel name="Fees.viewfees" bundle="FeesUIResources">
							</mifos:mifoslabel> </span>
					</td>
				</tr>
			</table>
			<table width="95%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td align="left" valign="top" class="paddingL15T15">
						<table width="95%" border="0" cellpadding="3" cellspacing="0">
							<tr>
								<td class="headingorange">
									<span class="headingorange"> <mifos:mifoslabel name="Fees.viewfees" bundle="FeesUIResources"></mifos:mifoslabel> </span>
								</td>
							</tr>
							<tr>
								<td class="fontnormalbold">
									<span class="fontnormal"> <mifos:mifoslabel name="Fees.ViewFeesInstruction" bundle="FeesUIResources">
										</mifos:mifoslabel> <html-el:link href="javascript:fnOnNewFee(feeactionform)">
											<mifos:mifoslabel name="Fees.smalldefinenewfee" bundle="FeesUIResources">
											</mifos:mifoslabel>
										</html-el:link> <br> </span> <span class="fontnormalbold"> <span class="fontnormalbold"> <br> </span> </span> <span class="fontnormalbold"> </span> <span class="fontnormal"> </span> <span class="fontnormalbold"> <span class="fontnormalbold"> <font
											class="fontnormalRedBold"> <html-el:errors bundle="FeesUIResources" /> </font> </span> </span> <span class="fontnormalbold"> <span class="fontnormalbold"> <mifos:mifoslabel name="Fees.productfees" bundle="FeesUIResources">
											</mifos:mifoslabel><br> </span> </span> <span class="fontnormalbold"> </span>
									<table width="90%" border="0" cellspacing="0" cellpadding="0">
										<c:forEach var="productFee" items="${sessionScope.productFees}" >
											<tr class="fontnormal">
												<td width="1%">
													<img src="pages/framework/images/bullet_circle.gif" width="9" height="11">
												</td>
												<td width="99%">
													<html-el:link href="javascript:fnOnView(${productFee.feeId})">
														<c:out value="${productFee.feeName}" />
													</html-el:link>
													(
													<c:out value="${productFee.categoryType.name}" />
													)
													<c:if test="${productFee.feeStatus.id == FeeStatus.INACTIVE.value}">
														<img src="pages/framework/images/status_closedblack.gif" width="8" height="9">&nbsp;
															<%--<mifos:mifoslabel name="Fees.inactive" bundle="FeesUIResources" />--%>
															<c:out value="${productFee.feeStatus.name}"/>
													</c:if>
												</td>
											</tr>
										</c:forEach>
									</table>
									<br>
									<mifos:mifoslabel name="Fees.clientfees" bundle="FeesUIResources">
									</mifos:mifoslabel>
									<br>

									<table width="90%" border="0" cellspacing="0" cellpadding="0">
										<c:forEach var="clientFee" items="${sessionScope.customerFees}" >
											<tr class="fontnormal">
												<td width="1%">
													<img src="pages/framework/images/bullet_circle.gif" width="9" height="11">
												</td>
												<td width="99%">
													<html-el:link href="javascript:fnOnView(${clientFee.feeId})">
														<c:out value="${clientFee.feeName}" />
													</html-el:link>
													(
													<c:out value="${clientFee.categoryType.name}" />
													)
													<c:if test="${clientFee.feeStatus.id == FeeStatus.INACTIVE.value}">
														<img src="pages/framework/images/status_closedblack.gif" width="8" height="9">&nbsp;
														<c:out value="${clientFee.feeStatus.name}"/>
													</c:if>
												</td>
											</tr>
										</c:forEach>
									</table>
								</td>
							</tr>
							<html-el:hidden property="method" value="get" />
							<html-el:hidden property="feeId" value="" />
						</table>
						<br>
					</td>
				</tr>
			</table>
			<br>
		</html-el:form>
	</tiles:put>
</tiles:insert>
