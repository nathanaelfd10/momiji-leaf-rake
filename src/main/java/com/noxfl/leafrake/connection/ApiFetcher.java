/**
 * 
 */
package com.noxfl.leafrake.connection;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

/**
 * @author Fernando Nathanael
 *
 */
public interface ApiFetcher {

	/**
	 * Send a POST request to an end point, then returns its response in the form of
	 * JSONObject.
	 * 
	 * @param body
	 * @param headers
	 * @param targetUrl
	 * @return
	 * @throws IOException
	 */
	public JSONObject fetchPost(String body, Map<String, String> headers, String targetUrl) throws IOException;

}