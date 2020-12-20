package it.freshfruits.domain.vo;

import it.freshfruits.util.ValidationUtils;

public final class ContactInformationImpl implements ContactInformation {

    public static class Builder {
        private String phoneNumber = "";
        private String mobilePhoneNumber = "";
        private String faxNumber = "";
        private String email = "";

        public Builder(String phoneNumber, String mobilePhoneNumber, String faxNumber, String email) {
            ValidationUtils.validatePhoneNumber(phoneNumber);
            ValidationUtils.validateMobilePhoneNumber(mobilePhoneNumber);
            ValidationUtils.validateFaxNumber(faxNumber);
            ValidationUtils.validateEmail(email);
            this.phoneNumber = phoneNumber;
            this.mobilePhoneNumber = mobilePhoneNumber;
            this.faxNumber = faxNumber;
            this.email = email;
        }

        public ContactInformationImpl build() {
            return new ContactInformationImpl(this);
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public String getEmail() {
        return email;
    }

    public String toString() {
        return new StringBuilder().append("\nphoneNumber:").append(phoneNumber).append("\nmobilePhoneNumber:").append(mobilePhoneNumber).append("\nfaxNumber:").append(faxNumber).append("\nemail:")
                .append(email).toString();
    }

    private ContactInformationImpl(Builder builder) {
        this.email = builder.email;
        this.faxNumber = builder.faxNumber;
        this.mobilePhoneNumber = builder.mobilePhoneNumber;
        this.phoneNumber = builder.phoneNumber;
    }

    private String phoneNumber = "";
    private String mobilePhoneNumber = "";
    private String faxNumber = "";
    private String email = "";
}
