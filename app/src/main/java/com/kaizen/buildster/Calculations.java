package com.kaizen.buildster;


 class Calculations {
    private int a;
    private int b;
    private int c;

    int[] CastCon(int quantity){
        a = quantity * 4;
        double ba = quantity * 0.63;
        b = (int) ba;
        c = quantity;

        return new int[]{a,b,c};
    }

    int[] ClayBlo(int quantity){
        a = quantity * 75;
        b = quantity/4;
        double ca =quantity * 0.05;
        c = (int)ca;

        return new int[]{a,b,c};
    }

    int[] HardCore(int quantity){
        double aa = quantity * 0.152;
        a = (int) aa;

        return new int[]{a};
    }

    int[] CastOver(int quantity){
        a = quantity * 4;
        double ba = quantity * 0.63;
        b = (int) ba;
        c = quantity;

        return new int[]{a,b,c};
    }

    int[] ClayBloc(int quantity){
        a = quantity * 40;
        double ba = quantity * 5.5;
        b = (int) ba;
        double ca = quantity * 0.09;
        c = (int) ca;

        return new int[]{a,b,c};
    }

    int[] KirForm(int quantity){
        double aa = quantity * 4.2;
        a = (int) aa;
        b = 15;

        return new int[]{a,b,c};
    }

    int[] CastConc(int quantity){
        a = quantity * 4;
        double ba = quantity * 0.63;
        b = (int) ba;
        c = quantity;
        int d = 44;
        int e = 33;
        int f = 4;

        return new int[]{a,b,c, d, e, f};
    }

    int[] SawnCy(int quantity){
        double aa = quantity * 4.2;
        a = (int) aa;
        return new int[]{a};
    }

    int[] SawnCyp(int quantity){
        a = quantity * 3;

        return new int[]{a};
    }

    int[] SawnCypr(int quantity){
        a = quantity * 3;

        return new int[]{a};
    }

    int[] RoofingSheets(int quantity){
        double aa = quantity * 2.28;
        a = (int) aa;

        return new int[]{a};
    }

    int[] FasciaBoards(int quantity){
        a = quantity / 3;

        return new int[]{a};
    }

    int[] FloorFinish(int quantity){
        double aa = quantity * 0.2;
        double ba = quantity * 0.025;
        a = (int) aa;
        b = (int) ba;

        return new int[]{a,b};
    }

    int[] TilesFloor(int quantity){
        a = 0;
        double ba = quantity * 0.5;
        b = (int) ba;

        return new int[]{a,b,c};
    }

    int[] TilesWalls(int quantity){
        a = 0;
        double ba = quantity * 0.5;
        b = (int) ba;

        return new int[]{a,b};
    }

    int[] MetalEaves(int quantity){
        a = quantity / 10;

        return new int[]{a};
    }

    int[] TimberJoi(int quantity){
        a = quantity / 4 ;

        return new int[]{a};
    }

    int[] Ceiling(int quantity){
        double aa = quantity * 0.2;
        double ba = quantity * 0.025;
        a = (int) aa;
        b = (int) ba;

        return new int[]{a,b};
    }

    int[] Plaster(int quantity){
        double aa = quantity * 0.1;
        double ba = quantity * 0.02;
        a = (int) aa;
        b = (int) ba;

        return new int[]{a,b};
    }

    int[] Rendering(int quantity){
        double aa = quantity * 0.1;
        double ba = quantity * 0.02;
        a = (int) aa;
        b = (int) ba;

        return new int[]{a,b};
    }
}
