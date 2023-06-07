package ewallet.ewallet.constant;

public enum MessageResponse {
    SUCCESS_CREATE {
        @Override
        public String toString() {
            return "SUCCESS CREATE ";
        }
    },

    SUCCESS_GET_DATA {
        public String toString() {
            return "SUCCESS GET ";
        }
    },

    SUCCESS_UPDATE_DATA {
        public String toString() {
            return "SUCCESS UPDATE ";
        }
    },


    SUCCESS_DELETE_DATA {
        public String toString() {
            return "SUCCESS DELETE ";
        }
    },
}
