import styles from "./DetailsPanel.module.css";
import cctvStyles from './CctvDetails.module.css';
import {useEffect, useState} from "react";
import {apiSlice} from "../../../store/slices/apiSlice.js";

/*
 * When users select traffic camera markers, they are given the option to request
 * the latest image from that camera.
 */
function CctvDetails({details}) {
    const [showImage, setShowImage] = useState(false);

    console.log('Show image:');
    console.log(showImage);

    // Reset the display when clicking on a new marker.
    useEffect(() => setShowImage(false), [details.systemCodeNumber]);

    const [trigger, {data: image, isFetching, isSuccess, isError}] = apiSlice.endpoints.getCctvImage.useLazyQuery();

    const handleClick = () => {
        trigger(details.systemCodeNumber);
        setShowImage(true);
    };

    // A button that allows users to request the latest image.
    let content = <button onClick={handleClick}>View</button>;

    // Indicate that the requested image is loading.
    if (isFetching) content = <p>Loading...</p>;

    // Error message in case the image fails to load.
    else if (isError) content = <p>Failed to load image...</p>;

    // Show the CCTV image
    else if(isSuccess && showImage) {
        const imageUrl = URL.createObjectURL(image);
        content = <img src={imageUrl} alt={details.systemCodeNumber} className={cctvStyles.image} />;
    }

    return (
        <div className={cctvStyles.cctv}>
            <p className={styles['info--minor']}>{details.systemCodeNumber}</p>
            <p className={styles['info--main']}>{details.longDescription}</p>
            {content}
        </div>
    );
}

export default CctvDetails;