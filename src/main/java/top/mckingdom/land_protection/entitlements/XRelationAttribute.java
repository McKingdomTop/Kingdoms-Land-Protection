package top.mckingdom.land_protection.entitlements;

import org.kingdoms.constants.group.Group;
import org.kingdoms.constants.group.Kingdom;
import org.kingdoms.constants.group.model.relationships.RelationAttribute;
import org.kingdoms.constants.group.model.relationships.StandardRelationAttribute;
import org.kingdoms.constants.namespace.Namespace;
import org.kingdoms.main.Kingdoms;

public class XRelationAttribute extends RelationAttribute {

    public XRelationAttribute(Namespace namespace) {
        super(namespace);

    }
    static XRelationAttribute reg(Namespace namespace, int hash) {
        XRelationAttribute attr = new XRelationAttribute(namespace);
        attr.setHash(hash);
        Kingdoms.get().getRelationAttributeRegistry().register(attr);
        return attr;
    }
    @Override
    public boolean hasAttribute(Group group, Group group1) {
        return StandardRelationAttribute.hasAttribute(this, group, group1);
    }

    public boolean hasAttribute(Kingdom kingdom, Kingdom kingdom1) {
        return StandardRelationAttribute.hasAttribute(this, kingdom, kingdom1);
    }

}
