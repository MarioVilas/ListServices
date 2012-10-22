/*
 * Copyright (c) 2009-2011, Mario Vilas
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *     * Redistributions of source code must retain the above copyright notice,
 *       this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice,this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the copyright holder nor the names of its
 *       contributors may be used to endorse or promote products derived from
 *       this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

import java.security.Provider;
import java.security.Security;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		if (args.length == 0) {
			dump(getServiceTypes());
		} else {
			dump(args);
		}
	}

	private static void dump(String[] types) {
		for (String type : types) {
			System.out.println(type + ":");
			for (String impl : getCryptoImpls(type)) {
				System.out.println("\t" + impl);
			}
			System.out.println();
		}
	}

	// These two methods below were adapted from:
	// http://www.exampledepot.com/egs/java.security/ListServices.html

	// This method returns all available services types
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String[] getServiceTypes() {
	    Set result = new HashSet();

	    // All all providers
	    Provider[] providers = Security.getProviders();
	    for (int i=0; i<providers.length; i++) {
	        // Get services provided by each provider
	        Set keys = providers[i].keySet();
	        for (Iterator it=keys.iterator(); it.hasNext(); ) {
	            String key = (String)it.next();
	            key = key.split(" ")[0];

	            if (key.startsWith("Alg.Alias.")) {
	                // Strip the alias
	                key = key.substring(10);
	            }
	            int ix = key.indexOf('.');
	            result.add(key.substring(0, ix));
	        }
	    }
	    return (String[])result.toArray(new String[result.size()]);
	}

	// This method returns the available implementations for a service type
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String[] getCryptoImpls(String serviceType) {
	    Set result = new HashSet();

	    // All all providers
	    Provider[] providers = Security.getProviders();
	    for (int i=0; i<providers.length; i++) {
	        // Get services provided by each provider
	        Set keys = providers[i].keySet();
	        for (Iterator it=keys.iterator(); it.hasNext(); ) {
	            String key = (String)it.next();
	            key = key.split(" ")[0];

	            if (key.startsWith(serviceType+".")) {
	                result.add(key.substring(serviceType.length()+1));
	            } else if (key.startsWith("Alg.Alias."+serviceType+".")) {
	                // This is an alias
	                result.add(key.substring(serviceType.length()+11));
	            }
	        }
	    }
	    return (String[])result.toArray(new String[result.size()]);
	}
}
