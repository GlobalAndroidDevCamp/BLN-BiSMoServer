package org.bismoapp.resources;

import org.bismoapp.models.NextShow;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;

public class RetrieveNextShow extends ServerResource{

 @Get("json")
  public Representation acceptRepresentation(Representation entity){
	 String tvId = (String) getRequest().getAttributes().get("tvId");
	 //TODO: check that the TVID exists
	 
	 Objectify ofy = ObjectifyService.begin();

	 NextShow showFetched = ofy.query(NextShow.class).filter("tvId =", tvId).get();

     try {
    	 JSONObject jsonObj = new JSONObject();
    	 if(showFetched == null){
    		 jsonObj.put("error", "no upcoming shows");
    	 }
    	 else{
    		 jsonObj = showFetched.toJSON();
    	 }
      	JsonRepresentation jsonRep = new JsonRepresentation(jsonObj);
   	 	return jsonRep;
     } catch (Exception e) {
         e.printStackTrace();
     }
return null;
}
}