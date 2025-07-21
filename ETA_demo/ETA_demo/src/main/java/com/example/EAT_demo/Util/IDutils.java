package com.example.EAT_demo.Util;

import java.util.UUID;

public class IDutils {
    public static int getID()
    {
        return Math.abs(UUID.randomUUID().toString().hashCode());
    }

}
