package genericCheckpointing.server;

import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;

public interface StoreI extends StoreRestoreI {
	void writeObj(MyAllTypesFirst aRecord, int authID, String wireFormat);
    void writeObj(MyAllTypesSecond bRecord, int authID, String wireFormat);
}
