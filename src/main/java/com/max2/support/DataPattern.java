package com.max2.support;

import org.springframework.util.Assert;

/**
 * Data patterns helper model to store patterns and corresponding mapping name 
 **/
public class DataPattern {
        private String[] patterns;
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
