/**
 * “Copyright 2012 Megam Systems”
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 **/
package org.megam.deccanplato.provider.salesforce.crm.handler;

import static org.megam.deccanplato.provider.Constants.*;
import static org.megam.deccanplato.provider.salesforce.crm.Constants.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.entity.ContentType;
import org.megam.deccanplato.http.TransportMachinery;
import org.megam.deccanplato.http.TransportResponse;
import org.megam.deccanplato.http.TransportTools;
import org.megam.deccanplato.provider.BusinessActivity;
import org.megam.deccanplato.provider.Constants;
import org.megam.deccanplato.provider.core.BusinessActivityInfo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * This class implements the business activity of Salesforcecrm solution method.
 * this class has four methods, to implement business functions, like create, update,
 * lisd and delete. 
 * 
 * @author pandiyaraja
 */
public class SolutionImpl implements BusinessActivity{
	private static final String SALESFORCE_SOLUTION_URL="/services/data/v25.0/sobjects/Solution/";
	private Map<String, String> args;
	private BusinessActivityInfo bizInfo;
/* (non-Javadoc)
 * @see org.megam.deccanplato.provider.BusinessActivity#setArguments(org.megam.deccanplato.provider.core.BusinessActivityInfo, java.util.Map)
 */
@Override
public void setArguments(BusinessActivityInfo tempBizInfo,
		Map<String, String> tempArgs) {
	
	this.args = tempArgs;
	this.bizInfo = tempBizInfo;
	
}

/* (non-Javadoc)
 * @see org.megam.deccanplato.provider.BusinessActivity#run()
 */
@Override
public Map<String, String> run() {
	Map<String, String> outMap=null;
	switch (bizInfo.getActivityFunction()) {
	case CREATE:
		outMap=create();
		break;
	case LIST:
		outMap=list();
		break;
	case UPDATE:
		outMap=update();
		break;
	case DELETE:
		outMap=delete();
		break;
	default:
		break;
	}
	return outMap;
}

/**
 * this method creates an solution in salesforce.com and returns that solution id.
 * This method gets input from a MAP(contains json data) and returns a MAp.
 * @param outMap 
 */
private Map<String, String> create() {
	
    final String SALESFORCE_CREATE_SOLUTION_URL = args.get(INSTANCE_URL)+SALESFORCE_SOLUTION_URL;
	Map<String, String> outMap=new HashMap<>();
	Map<String,String> header=new HashMap<String,String>();
    header.put(S_AUTHORIZATION, S_OAUTH+args.get(ACCESS_TOKEN));
			
    Map<String, Object> userAttrMap = new HashMap<String, Object>();
    userAttrMap.put(S_SOLUTIONNAME, args.get(SOLUTION_NAME));
    userAttrMap.put(S_STATUS, args.get(STATUS));
            
    TransportTools tst=new TransportTools(SALESFORCE_CREATE_SOLUTION_URL, null, header);
    Gson obj = new GsonBuilder().setPrettyPrinting().create();
    tst.setContentType(ContentType.APPLICATION_JSON, obj.toJson(userAttrMap));
    try {
		String response=TransportMachinery.post(tst).entityToString();
		outMap.put(OUTPUT, response);	
	
	} catch (ClientProtocolException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	return outMap;		
}

/**
 * this method lists all solution in salesforce.com and returns a list of all solution details.
 * This method gets input from a MAP(contains json data) and returns a MAp.
 * @param outMap 
 */
private Map<String, String> list() {
	Map<String, String> outMap=new HashMap<>();
	final String SALESFORCE_LIST_SOLUTION_URL = args.get(INSTANCE_URL)
			+ "/services/data/v25.0/query/?q=SELECT+SolutionName,Id+FROM+Solution";
	Map<String, String> header = new HashMap<String, String>();
	header.put(S_AUTHORIZATION, S_OAUTH + args.get(ACCESS_TOKEN));

	TransportTools tst = new TransportTools(SALESFORCE_LIST_SOLUTION_URL, null,
			header);
	try {
		String response = TransportMachinery.get(tst).entityToString();
		outMap.put(OUTPUT, response);
	} catch (ClientProtocolException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} catch (URISyntaxException e) {
		e.printStackTrace();
	}
	return outMap;
}

/**
 * This method updates a solution in salesforce.com and returns a success message with solution id.
 * This method gets input from a MAP(contains json data) and returns a MAp.
 * @param outMap 
 */
private Map<String, String> update() {
	Map<String, String> outMap=new HashMap<>();
final String SALESFORCE_UPDATE_SOLUTION_URL = args.get(INSTANCE_URL)+SALESFORCE_SOLUTION_URL+args.get(ID);
	
	Map<String,String> header=new HashMap<String,String>();
    header.put(S_AUTHORIZATION, S_OAUTH+args.get(ACCESS_TOKEN));
    Map<String, Object> userAttrMap = new HashMap<String, Object>();        
    userAttrMap.put(S_SOLUTIONNAME, args.get(SOLUTION_NAME));
            
    TransportTools tst=new TransportTools(SALESFORCE_UPDATE_SOLUTION_URL, null, header);
    Gson obj = new GsonBuilder().setPrettyPrinting().create();
    tst.setContentType(ContentType.APPLICATION_JSON, obj.toJson(userAttrMap));
    try {
		 TransportMachinery.patch(tst);
		 outMap.put(OUTPUT, UPDATE_STRING+args.get(ID));
	
	} catch (ClientProtocolException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	return outMap;
}

/**
 * this method deletes an solution in salesforce.com and returns a success message with deleted solution id.
 * This method gets input from a MAP(contains json data) and returns a MAp.
 * @param outMap 
 */
private Map<String, String> delete() {
	Map<String, String> outMap=new HashMap<>();
	final String SALESFORCE_DELETE_SOLUTION_URL = args.get(INSTANCE_URL)
			+SALESFORCE_SOLUTION_URL+args.get(ID);
	Map<String, String> header = new HashMap<String, String>();
	header.put(S_AUTHORIZATION, S_OAUTH+ args.get(ACCESS_TOKEN));

	TransportTools tst = new TransportTools(SALESFORCE_DELETE_SOLUTION_URL, null,
			header);
	try {
		 TransportMachinery.delete(tst);
		 outMap.put(OUTPUT, DELETE_STRING+args.get(ID));
	} catch (ClientProtocolException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	return outMap;
}

/* (non-Javadoc)
 * @see org.megam.deccanplato.provider.BusinessActivity#name()
 */
@Override
public String name() {
	return "solution";
}

}
