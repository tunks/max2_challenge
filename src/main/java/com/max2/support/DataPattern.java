package com.max2.support;

import org.springframework.util.Assert;

/**
 * Data patterns utility model to store and map field names and value regex patterns
 * @author ebrimatunkara
 **/
public class DataPattern {
	    /**
	     * Field value regex experessions 
	     **/
        private String[] patterns;
        /**
         * Corresponding field names  with same array index as the field value regex
         */
        private String[] patternsMapper={};
		
        public DataPattern(String[] patterns, String[] patternsMapper) {
			this.patterns = patterns;
			this.patternsMapper = patternsMapper;
			Assert.isTrue(patterns.length == patternsMapper.length, "Patterns and mappers should be equal length");
		}

		public String[] getPatterns() {
			return patterns;
		}

		public void setPatterns(String[] patterns) {
			this.patterns = patterns;
		}

		public String[] getPatternsMapper() {
			return patternsMapper;
		}

		public void setPatternsMapper(String[] patternsMapper) {
			this.patternsMapper = patternsMapper;
		}
}
