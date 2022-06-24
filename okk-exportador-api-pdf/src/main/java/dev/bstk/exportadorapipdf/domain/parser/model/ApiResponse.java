package dev.bstk.exportadorapipdf.domain.parser.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.json.bind.annotation.JsonbProperty;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponse {

    @JsonbProperty("meta")
    private Meta meta;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Meta implements Serializable {

        @JsonbProperty("status")
        private int status;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
