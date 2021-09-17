package Game.utility;


import Game.model.AppUser;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToJwtConverter implements Converter<String, AppUser> {

    private JwtConverter converter;

    public StringToJwtConverter(JwtConverter converter) {
        this.converter = converter;
    }

    @Override
    public AppUser convert(String s) {
        AppUser user = null;

        if (s != null && s.startsWith("Bearer ")) {
            String token = s.substring(7);
            user = converter.getUserFromToken(token);
        }

        return user;
    }
}