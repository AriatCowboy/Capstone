import {
  Button,
  Image,
  Modal,
  Header,
  Select,
  Tab,
  Label,
  Grid,
} from "semantic-ui-react";

import C1 from "../picturecomponents/Acedemiic.png";
import C2 from "../picturecomponents/HealthHarbor.png";
import C3 from "../picturecomponents/LeisureMachine.png";
import C4 from "../picturecomponents/SocialCells.png";
import C5 from "../picturecomponents/UXGallery.png";
import C6 from "../picturecomponents/BrightMiner.png";
import C7 from "../picturecomponents/Digitalic.png";
import C8 from "../picturecomponents/Earthzy.png";
import C9 from "../picturecomponents/EcoAlley.png";
import C10 from "../picturecomponents/Modaxy.png";
import C11 from "../picturecomponents/Tourux.png";
import C12 from "../picturecomponents/CargoAcer.png";
import C13 from "../picturecomponents/PowerLy.png";
import C14 from "../picturecomponents/RivalIndustry.png";
import C15 from "../picturecomponents/SupplyPhase.png";
import C16 from "../picturecomponents/UltraFoundry.png";
import C17 from "../picturecomponents/Agribuilder.png";
import C18 from "../picturecomponents/CoalStove.png";
import C19 from "../picturecomponents/PetroYield.png";
import C20 from "../picturecomponents/ProsperityFuel.png";
import C21 from "../picturecomponents/TimberFly.png";
import C22 from "../picturecomponents/Cammunition.png";
import C23 from "../picturecomponents/Grandlytics.png";
import C24 from "../picturecomponents/Pharmanetic.png";
import C25 from "../picturecomponents/Protectice.png";
import C26 from "../picturecomponents/SimplySentient.png";

function CompanyImage({ companyId, format }) {
  let image = null;
  console.log(companyId);
  switch (companyId) {
    case 1:
      image = C1;
      break;

    case 2:
      image = C2;
      break;

    case 3:
      image = C3;
      break;

    case 4:
      image = C4;
      break;

    case 5:
      image = C5;
      break;

    case 6:
      image = C6;
      break;

    case 7:
      image = C7;
      break;

    case 8:
      image = C8;
      break;

    case 9:
      image = C9;
      break;

    case 10:
      image = C10;
      break;

    case 11:
      image = C11;
      break;

    case 12:
      image = C12;
      break;

    case 13:
      image = C13;
      break;

    case 14:
      image = C14;
      break;

    case 15:
      image = C15;
      break;

    case 23:
      image = C23;
      break;

    default:
      image = null;
  }
  return format === "modal" ? (
    <Image size="large" src={image} wrapped />
  ) : (
    <Image class="ui large image" src={C5}></Image>
  );
}

export default CompanyImage;
