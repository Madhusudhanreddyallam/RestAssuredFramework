package pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Formula1CircuitData {
	
    @JsonProperty("MRData")
	private MRData MRData;

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class MRData{
		
		private String xmlns;
		private String series;
		private String url;
		private String limit;
		private String offset;
		private String total;
	    @JsonProperty("CircuitTable")
		private CircuitTable CircuitTable;
		
		@Getter
		@Setter
		@AllArgsConstructor
		@NoArgsConstructor
		@JsonInclude(JsonInclude.Include.NON_NULL)
		public static class CircuitTable{
			
			private String season;
		    @JsonProperty("Circuits")
			private List<Circuits> Circuits;
			
			@Getter
			@Setter
			@AllArgsConstructor
			@NoArgsConstructor
			@JsonInclude(JsonInclude.Include.NON_NULL)
			public static class Circuits{
				private String circuitId;
				private String url;
				private String circuitName;
			    @JsonProperty("Location")
				private Location Location;
				
				@Getter
				@Setter
				@AllArgsConstructor
				@NoArgsConstructor
				@JsonInclude(JsonInclude.Include.NON_NULL)
				public static class Location{
					private String lat;
				    @JsonProperty("long")
					private String _long;
					private String locality;
					private String country;

				}
			}
			
		}	

	}
		
	
}
