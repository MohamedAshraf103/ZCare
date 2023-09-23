package org.ZCare.DTO;

public class CareGiverPhone {
			private long id,careGiverId;
			private String phone;
			public CareGiverPhone() {}
			
			public CareGiverPhone(long id, long careGiverId, String address) {
				super();
				this.id = id;
				this.careGiverId = careGiverId;
				this.phone = address;
			}
			

			public long getId() {
				return id;
			}
			public void setId(long id) {
				this.id = id;
			}
			public long getCareGiverId() {
				return careGiverId;
			}
			public void setCareGiverId(long careGiverId) {
				this.careGiverId = careGiverId;
			}
			public String getPhone() {
				return phone;
			}
			public void setPhone(String phone) {
				this.phone = phone;
			}
			
}
