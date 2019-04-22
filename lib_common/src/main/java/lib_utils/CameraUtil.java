package lib_utils;

import android.content.Intent;

/**
 * @author taozhu5
 * @date 2019/4/15 14:45
 * @description 描述
 */
public class CameraUtil {
    private void getCamera() {
        Intent intent = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
       // startActivityForResult(intent, IMAGE_REQUEST_CODE);
    }
}
