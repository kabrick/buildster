package com.kaizen.buildster;



public class ConversionCalc {

    private double[] returnValue;

    public double[] Area(int value, String unit){
        double feet, yards, meters, km, miles, acres;
        switch (unit){
            case "Square Feet":
                feet = 0;
                yards = 0.111 * value;
                meters = 0.0929 * value;
                miles = 0.00000003587 * value;
                km = 0.0000000929 * value;
                acres = 0.00002296 * value;
                returnValue = new double[]{feet, yards, meters, miles, km, acres};
                break;
            case "Square Yards":
                yards = 0;
                feet = 9 * value;
                meters = 0.836 * value;
                miles = 0.0000003228 * value;
                km = 0.0000008361 * value;
                acres = 0.000207 * value;
                returnValue = new double[]{feet, yards, meters, miles, km, acres};
                break;
            case "Square Meters":
                meters = 0;
                feet = 10.764 * value;
                yards = 1.196 * value;
                miles = 0.0000003861 * value;
                km = 0.000001 * value;
                acres = 0.000247 * value;
                returnValue = new double[]{feet, yards, meters, miles, km, acres};
                break;
            case "Square Kilometers":
                km = 0;
                feet = 10763910.4 * value;
                yards = 1195990.05 * value;
                meters = 1000000 * value;
                miles = 0.386102 * value;
                acres = 247.105 * value;
                returnValue = new double[]{feet, yards, meters, miles, km, acres};
                break;
            case "Square Miles":
                miles = 0;
                feet = 27878400 * value;
                yards = 3097600 * value;
                meters = 2589988.11 * value;
                km = 2.589988 * value;
                acres = 640 * value;
                returnValue = new double[]{feet, yards, meters, miles, km, acres};
                break;
            case "Acres":
                acres = 0;
                feet = 43560 * value;
                yards = 4840 * value;
                meters = 4046.86 * value;
                miles = 0.00156 * value;
                km = 0.0040469 * value;
                returnValue = new double[]{feet, yards, meters, miles, km, acres};
                break;
            default:
                feet =  0;
                yards = 0;
                meters = 0;
                miles = 0;
                km = 0;
                acres = 0;

                returnValue = new double[]{feet, yards, meters, miles, km, acres};
                break;
        }
        return returnValue;
    }

    public double[] Energy(int value, String unit){

        double joules, kilojoules, watthours, kilowatt;
        switch (unit){
            case "Joules":
                joules = 0;
                kilojoules = 0.001 * value;
                watthours = 0.000278 * value;
                kilowatt = 0.0000002778 * value;
                returnValue = new double[]{joules, kilojoules, watthours, kilowatt};
                break;
            case "Kilojoules":
                kilojoules = 0;
                watthours = 0.278 * value;
                kilowatt = 0.000278 * value;
                joules = 1000 * value;
                returnValue = new double[]{joules, kilojoules, watthours, kilowatt};
                break;
            case "Watt Hours":
                watthours = 0;
                kilowatt = 0.001 * value;
                joules = 3600 * value;
                kilojoules = 3.6 * value;
                returnValue = new double[]{joules, kilojoules, watthours, kilowatt};
                break;
            case "Kilowatt Hours":
                kilowatt = 0;
                joules = 3600000 * value;
                kilojoules = 3600 * value;
                watthours = 1000 * value;
                returnValue = new double[]{joules, kilojoules, watthours, kilowatt};
                break;
            default:
                joules = 0;
                kilojoules = 0;
                kilowatt = 0;
                watthours = 0;
                returnValue = new double[]{joules, kilojoules, watthours, kilowatt};
                break;
        }
        return returnValue;
    }

    public double[] Density(int value, String unit){
        double pcf, kcm, kl;
        switch (unit){
            case "Pounds/cubic foot":
                pcf = 0;
                kcm = 16.0185 * value;
                kl = 0.0160185 * value;
                returnValue = new double[]{pcf, kcm, kl};
                break;
            case "Kilograms/cubic meter":
                kcm = 0;
                kl = 0.001 * value;
                pcf = 0.0624 * value;
                returnValue = new double[]{pcf, kcm, kl};
                break;
            case "Kilograms/liter":
                kl = 0;
                pcf = 62.428 * value;
                kcm = 1000 * value;
                returnValue = new double[]{pcf, kcm, kl};
                break;
            default:
                pcf = 0;
                kcm = 0;
                kl = 0;
                returnValue = new double[]{pcf, kcm, kl};
                break;
        }
        return returnValue;
    }

    public double[] Power(int value, String unit){
        double hp, watts, kilo;
        switch (unit){
            case "Horse Power":
                watts = 745.7 * value;
                kilo = 0.7457 * value;
                hp = 0;
                returnValue = new double[]{hp, watts, kilo};
                break;
            case "Watts":
                watts = 0;
                hp = 0.00134102 * value;
                kilo = 0.001 * value;
                returnValue = new double[]{hp, watts, kilo};
                break;
            case "Kilowatts":
                kilo = 0;
                hp = 1.34102 * value;
                watts = 1000 * value;
                returnValue = new double[]{hp, watts, kilo};
                break;
            default:
                hp = 0;
                watts = 0;
                kilo = 0;
                returnValue = new double[]{hp, watts, kilo};
                break;
        }
        return returnValue;
    }

    public double[] LiquidVolume(int value, String unit){
        double gallon, liters, ml;
        switch (unit){
            case "Gallons":
                gallon = 0;
                liters = 3.7854 * value;
                ml = 3785.41 * value;
                returnValue = new double[]{gallon, liters, ml};
                break;
            case "Liters":
                liters = 0;
                ml = 1000 * value;
                gallon = 0.264172 * value;
                returnValue = new double[]{gallon, liters, ml};
                break;
            case "Milliliters":
                ml = 0;
                gallon = 0.000264172 * value;
                liters = 0.001 * value;
                returnValue = new double[]{gallon, liters, ml};
                break;
            default:
                gallon = 0;
                liters = 0;
                ml = 0;
                returnValue = new double[]{gallon, liters, ml};
                break;
        }
        return returnValue;
    }

    public double[] Length(int value, String unit){
        double feet, inches, yards, metres, kilo;
        switch (unit){
            case "Feet":
                feet = 0;
                yards = 0.33333333 * value;
                metres = 0.3048 * value;
                kilo = 0.0003048 * value;
                inches = 12 * value;
                returnValue = new double[]{feet, inches, yards, metres, kilo};
                break;
            case "Inches":
                yards = 0.027777778 * value;
                inches = 0;
                metres = 0.0254 * value;
                kilo = 0.0000254 * value;
                feet = 0.0833333 * value;
                returnValue = new double[]{feet, inches, yards, metres, kilo};
                break;
            case "Yards":
                yards = 0;
                metres = 0.9144 * value;
                kilo = 0.0009144 * value;
                inches = 36 * value;
                feet = 3 * value;
                returnValue = new double[]{feet, inches, yards, metres, kilo};
                break;
            case "Metres":
                metres = 0;
                yards = 1.0936133 * value;
                kilo = 0.001 * value;
                inches = 39.3701 * value;
                feet = 3.28084 * value;
                returnValue = new double[]{feet, inches, yards, metres, kilo};
                break;
            case "Kilometers":
                kilo = 0;
                yards = 1093.6133 * value;
                metres = 1000 * value;
                inches = 39379.96 * value;
                feet = 3280.839895 * value;
                returnValue = new double[]{feet, inches, yards, metres, kilo};
                break;
            default:
                feet = 0;
                inches = 0;
                yards = 0;
                metres = 0;
                kilo = 0;
                returnValue = new double[]{feet, inches, yards, metres, kilo};
                break;
        }
        return returnValue;
    }

    public double[] Speed(int value, String unit){
        double kh, sound, light, knots, miles;
        switch (unit){
            case "Kilometers/Hour":
                kh = 0;
                miles = 0.6213712 * value;
                light = 0.0000000009265669 * value;
                knots = 0.53996 * value;
                sound = 0.000809848 * value;
                returnValue = new double[]{kh, sound, light, knots, miles};
                break;
            case "Speed of sound":
                sound = 0;
                light = 0.00000114412 * value;
                miles = 767.269 * value;
                knots = 666.739 * value;
                kh = 1234.8 * value;
                returnValue = new double[]{kh, sound, light, knots, miles};
                break;
            case "Speed of light":
                miles = 670616600 * value;
                light = 0;
                kh = 1079253000 * value;
                sound = 874030 * value;
                knots = 582750421.815 * value;
                returnValue = new double[]{kh, sound, light, knots, miles};
                break;
            case "Knots":
                knots = 0;
                miles = 1.150779 * value;
                kh = 1.852 * value;
                sound = 0.00149984 * value;
                light = 0.0000000017 * value;
                returnValue = new double[]{kh, sound, light, knots, miles};
                break;
            case "Miles/Hour":
                miles = 0;
                kh = 1.609 * value;
                light = 0.000000001491165 * value;
                knots = 0.8689762 * value;
                sound = 0.00130332 * value;
                returnValue = new double[]{kh, sound, light, knots, miles};
                break;
            default:
                kh = 0;
                sound = 0;
                light = 0;
                knots = 0;
                miles = 0;
                returnValue = new double[]{kh, sound, light, knots, miles};
                break;
        }
        return returnValue;
    }

    public double[] Temperature(int value, String unit){
        double cel, fah, kel;
        switch (unit){
            case "Celsius":
                cel = 0;
                fah = 33.8 * value;
                kel = 274.15 * value;
                returnValue = new double[]{cel, fah, kel};
                break;
            case "Fahrenheit":
                fah = 0;
                kel = 255.927778 * value;
                cel = -17.222222 * value;
                returnValue = new double[]{cel, fah, kel};
                break;
            case "Kelvin":
                kel = 0;
                cel = -272.15 * value;
                fah = -457.87 * value;
                returnValue = new double[]{cel, fah, kel};
                break;
            default:
                cel = 0;
                fah = 0;
                kel = 0;
                returnValue = new double[]{cel, fah, kel};
                break;
        }
        return returnValue;
    }

    public double[] Weight(int value, String unit){
        double grams, kilo, pounds, tonne;
        switch (unit){
            case "Grams":
                grams = 0;
                kilo = 0.001 * value;
                pounds = 0.00220462 * value;
                tonne = 0.000001 * value;
                returnValue = new double[]{grams, kilo, pounds, tonne};
                break;
            case "Kilograms":
                kilo = 0;
                pounds = 2.2046225 * value;
                grams = 1000 * value;
                tonne = 0.001 * value;
                returnValue = new double[]{grams, kilo, pounds, tonne};
                break;
            case "Pounds":
                pounds = 0;
                kilo = 0.4535924 * value;
                grams = 453.59237 * value;
                tonne = 0.000453592 * value;
                returnValue = new double[]{grams, kilo, pounds, tonne};
                break;
            case "Tonne":
                tonne = 0;
                kilo = 1000 * value;
                pounds =  2204.62 * value;
                grams = 1000000 * value;
                returnValue = new double[]{grams, kilo, pounds, tonne};
                break;
            default:
                grams = 0;
                kilo = 0;
                pounds = 0;
                tonne = 0;
                returnValue = new double[]{grams, kilo, pounds, tonne};
                break;
        }
        return returnValue;
    }

    public double[] Volume(int value, String unit){
        double cm, m, feet, yards, inches;
        switch (unit){
            case "Cubic Centimeters":
                cm = 0;
                yards = 1.307951 * value;
                m = 0.000001 * value;
                inches = 0.0610237 * value;
                feet = 3.53146667 * value;
                returnValue = new double[]{cm, m, feet, yards, inches};
                break;
            case "Cubic Meters":
                yards = 1.30795 * value;
                inches = 61023.7441 * value;
                feet = 35.314667 * value;
                cm = 1000000 * value;
                m = 0;
                returnValue = new double[]{cm, m, feet, yards, inches};
                break;
            case "Cubic Feet":
                yards = 0.037037 * value;
                feet = 0;
                m = 0.02831685 * value;
                inches = 1727.9999999994 * value;
                cm = 28316.846592 * value;
                returnValue = new double[]{cm, m, feet, yards, inches};
                break;
            case "Cubic Yards":
                yards = 0;
                m = 0.764555 * value;
                inches = 46656.000000513 * value;
                feet = 27.000000000306 * value;
                cm = 764554.857993 * value;
                returnValue = new double[]{cm, m, feet, yards, inches};
                break;
            case "Cubic Inches":
                yards = 2.14335 * value;
                m = 1.6387 * value;
                feet = 0.0005787 * value;
                inches = 0;
                cm = 16.387064 * value;
                returnValue = new double[]{cm, m, feet, yards, inches};
                break;
            default:
                cm = 0;
                m = 0;
                feet = 0;
                yards = 0;
                inches = 0;
                returnValue = new double[]{cm, m, feet, yards, inches};
                break;
        }
        return returnValue;
    }

    public double[] Arc(int radius, int angle){
        double area, length, perimeter;
        length = radius * (angle / 360);
        area = (Math.pow(radius,2) * (angle / 360)) / 2;
        perimeter = (radius * (angle / 360)) + (2 * radius);
        returnValue = new double[]{area, length, perimeter};
        return returnValue;
    }

    public double[] Rectangle(int length, int width){
        double area, perimeter;
        area = width * length;
        perimeter = 2 * (length + width);
        returnValue = new double[]{area, perimeter};
        return returnValue;
    }

    public double[] Circle(int value, String unit){
        double area, diameter, circumference;
        switch (unit){
            case "Area":
                area = 0;
                diameter = Math.sqrt((4 * value) / Math.PI);
                circumference = 2 * Math.sqrt(Math.PI * value);
                returnValue = new double[]{area, diameter, circumference};
                break;
            case "Diameter":
                diameter = 0;
                area = (Math.PI * Math.pow(value, 2)) * 4;
                circumference = Math.PI * value;
                returnValue = new double[]{area, diameter, circumference};
                break;
            case "Circumference":
                circumference = 0;
                area = Math.pow(value, 2) / (4 * Math.PI);
                diameter = value / Math.PI;
                returnValue = new double[]{area, diameter, circumference};
                break;
            default:
                area = 0;
                diameter = 0;
                circumference = 0;
                returnValue = new double[]{area, diameter, circumference};
                break;
        }
        return returnValue;
    }

    public double[] Trapezoid(int top, int base, int height){
        double area;
        area = ((top + base)/2) * height;
        returnValue = new double[]{area};
        return returnValue;
    }

    public double[] Triangle(int base, int height){
        double area;
        area = (height * base) / 2;
        returnValue = new double[]{area};
        return returnValue;
    }

    public double[] Cone(int height, int radius){
        double slant, surfacearea, volume;
        volume = Math.PI * Math.pow(radius,2) * (height / 3);
        surfacearea = Math.PI * radius * (radius + Math.sqrt(Math.pow(radius,2) + Math.pow(height,2)));
        slant = Math.sqrt(Math.pow(radius,2) + Math.pow(height,2));
        returnValue = new double[]{slant, surfacearea, volume};
        return returnValue;
    }

    public double[] Cube(int edge){
        double surfacearea, volume;
        surfacearea = 6 * Math.pow(edge, 2);
        volume = Math.pow(edge, 3);
        returnValue = new double[]{surfacearea, volume};
        return returnValue;
    }

    public double[] Cylinder(int height, int radius){
        double surfacearea, volume;
        surfacearea = (2 * Math.PI * radius * height) + (2 * Math.PI * Math.pow(radius, 2));
        volume = Math.PI * Math.pow(radius, 2) * height;
        returnValue = new double[]{surfacearea, volume};
        return returnValue;
    }

    public double[] Pyramid(int height, int width, int length){
        double surfacearea, volume;
        volume = (height * length * width)/3;
        surfacearea = (length * width) + (length * Math.sqrt(Math.pow(width/2,2) + Math.pow(height,2)))
                                                   + (width * Math.sqrt(Math.pow(length/2,2) + Math.pow(height, 2)));
        returnValue = new double[]{surfacearea, volume};
        return returnValue;
    }

    public double[] Sphere(int diameter){
        double surfacearea, volume;
        int radius = diameter / 2;
        surfacearea = 4 * Math.PI * Math.pow(radius, 2);
        volume = (4/3) * Math.PI * Math.pow(radius, 3);
        returnValue = new double[]{surfacearea, volume};
        return returnValue;
    }
}
