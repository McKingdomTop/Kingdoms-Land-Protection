package top.mckingdom.land_protection.entitlements;

import org.kingdoms.constants.group.model.relationships.RelationAttribute;
import org.kingdoms.constants.namespace.Namespace;

import java.util.HashMap;
import java.util.Map;

public class RelationAttributeRegister {



    public static top.mckingdom.land_protection.entitlements.XRelationAttribute register(String namespace, String keyword, int hash) {
        Namespace ns = new Namespace(namespace, keyword);
        return register(ns, hash);
    }

    public static top.mckingdom.land_protection.entitlements.XRelationAttribute register(Namespace namespace, int hash) {
        return top.mckingdom.land_protection.entitlements.XRelationAttribute.reg(namespace, hash);
    }


}
