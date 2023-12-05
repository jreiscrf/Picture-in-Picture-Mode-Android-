import {
  NativeModules,
  AppState,
  TouchableOpacity,
  View,
  Text,
  AppStateStatus,
} from "react-native";
import { useEffect, useState } from "react";
import Video from "react-native-video";

const PIPComponent = () => {
  const { PipModule } = NativeModules;
  const [hasDetectedBackground, setHasDetectedBackground] = useState(false);

  const enterPiPMode = () => {
    PipModule.enterPipMode();
  };

  return (
    <View style={{ flex: 1 }}>
      <Video
        source={{
          uri: "https://player.vimeo.com/external/538571502.sd.mp4?s=931e8b30977433ba260a9e08678ac56e13b73aa0&profile_id=165&oauth2_token_id=57447761",
        }}
        style={{
          width: "100%",
          height: 300,
          borderRadius: 20,
          padding: 10,
          marginTop: 20,
        }}
        pictureInPicture
        fullscreen
        posterResizeMode="cover"
        resizeMode="cover"
        repeat
        playInBackground
        poster="https://images.pexels.com/photos/1546035/pexels-photo-1546035.jpeg?auto=compress&cs=tinysrgb&w=1600"
      />

      {!hasDetectedBackground && (
        <TouchableOpacity
          style={{
            backgroundColor: "red",
            padding: 6,

            borderRadius: 6,
            marginTop: 20,
          }}
          onPress={() => {
            enterPiPMode();
          }}
        >
          <Text
            style={{
              color: "white",
              fontWeight: "bold",
              fontSize: 14,
              alignSelf: "center",
            }}
          >
            PIP Mode
          </Text>
        </TouchableOpacity>
      )}
    </View>
  );
};

export default PIPComponent;
