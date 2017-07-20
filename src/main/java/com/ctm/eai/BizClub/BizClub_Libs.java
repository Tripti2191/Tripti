package com.ctm.eai.BizClub;


import java.util.HashMap;

import org.apache.velocity.VelocityContext;

import com.ctm.core.CoreLibraries;
import com.ctm.logger.ConsoleReport;

public class BizClub_Libs extends CoreLibraries{
		public ConsoleReport consoleReport = new ConsoleReport();
		
		public VelocityContext createContextForReplacement(String[] attributes,String[] attrValues) {
			VelocityContext context = new VelocityContext();

			if(attributes.length!=(attrValues.length))
				System.out.println("Error :"+attributes.length+" not equal to "+(attrValues.length));
			else
				System.out.println("Match :"+attributes.length+" equal to "+(attrValues.length));
			
			for(int i=0;i<attributes.length;i++)
				context.put(attributes[i], attrValues[i]);
			
			return context;
		}
		
		
		
}
