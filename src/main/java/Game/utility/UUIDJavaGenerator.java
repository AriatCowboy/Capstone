package Game.utility;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UUIDJavaGenerator implements UUIDGenerator {
    @Override
    public String getUUIDString() {
        return UUID.randomUUID().toString();
    }
}
