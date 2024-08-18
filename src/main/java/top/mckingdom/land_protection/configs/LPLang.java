package top.mckingdom.land_protection.configs;

import org.kingdoms.locale.LanguageEntry;
import org.kingdoms.locale.messenger.DefinedMessenger;

public enum LPLang implements DefinedMessenger {

    LANDS_ELYTRA_PROTECTION("{$e}你进入了敌国领空, 无法使用鞘翅飞行!", 1)

    ;

    private final LanguageEntry languageEntry;
    private final String defaultValue;

    LPLang(String defaultValue, int... grouped) {
        this.defaultValue = defaultValue;
        this.languageEntry = DefinedMessenger.getEntry(null, this, grouped);
    }

    @Override
    public LanguageEntry getLanguageEntry() {
        return languageEntry;
    }

    @Override
    public String getDefaultValue() {
        return this.defaultValue;
    }
}
