package fr.picsou.narutoduel.components.list;

import java.util.List;

public class ArenaManager {
    private String ArenaName;
    private double x1;
    private double y1;
    private double z1;
    private float yaw1;
    private float pitch1;
    private double x2;
    private double y2;
    private double z2;
    private float yaw2;
    private float pitch2;
    private boolean disponible;
    private boolean open;
    public ArenaManager(String ArenaName, double x1, double y1, double z1, float yaw1, float pitch1, double x2, double y2, double z2, float yaw2, float pitch2, boolean disponible, boolean open) {
        this.ArenaName = ArenaName;
        this.x1 = x1;
        this.y1 = y1;
        this.z1 = z1;
        this.yaw1 = yaw1;
        this.pitch1 = pitch1;
        this.x2 = x2;
        this.y2 = y2;
        this.z2 = z2;
        this.yaw2 = yaw2;
        this.pitch2 = pitch2;
        this.disponible = disponible;
        this.open = open;
    }
}
