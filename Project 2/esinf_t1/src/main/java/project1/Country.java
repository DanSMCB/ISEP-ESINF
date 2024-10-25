package project1;

public class Country {

//    Area Code (FAO),Area;   ---> progect1.Country
//    progect1.Item Code (FAO),progect1.Item;    ---> progect1.Item
//    progect1.Year Code,progect1.Year;          ---> progect1.Year
//    Unit,progect1.Value;              ---> progect1.Value

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

