public class Country {

//    Area Code (FAO),Area;   ---> Country
//    Item Code (FAO),Item;    ---> Item
//    Year Code,Year;          ---> Year
//    Unit,Value;              ---> Value

        private int areaCode;
        private String area;

        public Country(int areaCode, String area) {
            this.areaCode = areaCode;
            this.area = area;
        }

        public int getAreaCode() {
            return areaCode;
        }

        public String getAreaCountry() {
            return area;
        }

        public void setAreaCode(int areaCode) {
            this.areaCode = areaCode;
        }

        public void setAreaCountry(String areaCountry) {
            this.area = areaCountry;
        }

    }

