package ewallet.ewallet.constant;

public enum MessageException {
    DATA_EXIST {
        @Override
        public String toString() {
            return "DATA ALREADY EXIST!";
        }
    },

    BAD_REQUEST {
        @Override
        public String toString() {
            return "BAD REQUEST!";
        }
    },

    NOT_FOUND {
        @Override
        public String toString() {
            return "DATA NOT FOUND!";
        }
    },

    INTERNAL_SERVER_ERROR {
        @Override
        public String toString() {
            return "INTERNAL SERVER ERROR!";
        }
    }
}
