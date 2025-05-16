package com.funxraysim.bodyprankscanner.spalsh;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.funxraysim.bodyprankscanner.R;
import com.funxraysim.bodyprankscanner.databinding.ActivityExploreBodyPartBinding;
import com.funxraysim.bodyprankscanner.model.BodyPartModel;
import com.funxraysim.bodyprankscanner.myAds.WebNavigationUtils;

import java.util.HashMap;
import java.util.Map;

public class ExploreBodyPartActivity extends BaseActivity {
    private ActivityExploreBodyPartBinding binding;
    private BodyPartModel selectedBodyPart;
    private View lastSelectedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityExploreBodyPartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbarLayout.headerTitle.setText("Explore Body Part");
        binding.toolbarLayout.btnBack.setOnClickListener(v -> myBackActivity());

        setClickListeners();
    }

    private void setClickListeners() {
        // Map buttons to their BodyPartModel
        Map<View, BodyPartModel> buttonModelMap = new HashMap<>();
        buttonModelMap.put(binding.btnHair, new BodyPartModel(
                "Hair",
                R.drawable.hair,
                "Hair is a protein filament that grows from follicles found in the dermis of the skin, primarily serving as a protective covering for the scalp. It plays a critical role in thermoregulation by insulating the head, helping to maintain body temperature in varying environmental conditions. Beyond its protective function, hair also serves as a sensory organ, with nerve endings around follicles detecting movement and environmental changes, contributing to tactile sensation. Additionally, hair has significant cultural and aesthetic importance, influencing personal identity and social interactions across different societies."
        ));
        buttonModelMap.put(binding.btnEar, new BodyPartModel(
                "Ear",
                R.drawable.ear,
                "The ear is a complex organ responsible for both hearing and maintaining balance, comprising three main parts: the outer ear, middle ear, and inner ear. The outer ear captures sound waves and funnels them through the ear canal to the eardrum, which vibrates to transmit sound to the middle ear’s tiny bones. These vibrations are then converted into electrical signals in the inner ear’s cochlea and sent to the brain for interpretation. Additionally, the inner ear’s vestibular system, with its semicircular canals, helps regulate balance by detecting head movements and positioning, ensuring coordination and stability."
        ));
        buttonModelMap.put(binding.btnEyebrows, new BodyPartModel(
                "Eyebrows",
                R.drawable.eyebrows,
                "Eyebrows are strips of hair above the eyes that serve both functional and expressive purposes, primarily protecting the eyes from sweat, rain, and debris. Their arched structure helps divert moisture and foreign particles away from the eyes, maintaining clear vision in adverse conditions. Eyebrows also play a significant role in non-verbal communication, as their movements can convey emotions such as surprise, anger, or confusion, enhancing facial expressions. Aesthetically, eyebrows are often groomed or styled, reflecting cultural beauty standards and personal identity across various contexts."
        ));
        buttonModelMap.put(binding.btnHand, new BodyPartModel(
                "Hand",
                R.drawable.hand,
                "Hands are remarkably versatile appendages that enable humans to interact with their environment through grasping, manipulating, and touching objects. Composed of bones, muscles, tendons, and nerves, the hand’s intricate structure allows for precise movements, such as writing, typing, or playing instruments, making it essential for daily tasks. The hand’s sensory capabilities, with thousands of nerve endings, provide tactile feedback, allowing humans to discern textures, temperatures, and pressure. Culturally, hands also hold symbolic significance, used in gestures for communication, rituals, and expressions of emotion across societies."
        ));
        buttonModelMap.put(binding.btnLeg, new BodyPartModel(
                "Leg",
                R.drawable.leg,
                "Legs are critical for human mobility, providing the structural support and power needed for walking, running, jumping, and standing. Comprising the thigh, lower leg, and foot, legs are anchored by strong bones like the femur, tibia, and fibula, which bear the body’s weight. Muscles such as the quadriceps, hamstrings, and calves work together to enable movement and maintain stability. Legs also play a role in balance, coordinating with the body’s sensory systems to keep us upright during dynamic activities."
        ));
        buttonModelMap.put(binding.btnKnee, new BodyPartModel(
                "Knee",
                R.drawable.knee,
                "The knee is a pivotal hinge joint that connects the thigh’s femur to the lower leg’s tibia, enabling critical movements like walking, running, and climbing. As one of the largest and most complex joints in the body, it comprises bones, cartilage, ligaments, and tendons that work together to provide stability and flexibility. The knee’s ability to flex, extend, and slightly rotate makes it essential for locomotion and weight-bearing activities. However, its complexity also makes it prone to injuries, such as ligament tears or arthritis."
        ));
        buttonModelMap.put(binding.btnTongue, new BodyPartModel(
                "Tongue",
                R.drawable.tongue,
                "The tongue is a muscular organ in the mouth critical for tasting, swallowing, and articulating speech. Composed of eight interwoven muscles, it is highly flexible and capable of precise movements, making it one of the most dynamic structures in the body. Covered in taste buds, the tongue detects five primary tastes—sweet, sour, salty, bitter, and umami—contributing to sensory experiences and nutritional choices. Its role in speech involves shaping sounds, enabling clear communication across languages."
        ));
        buttonModelMap.put(binding.btnFace, new BodyPartModel(
                "Face",
                R.drawable.face,
                "The face is a central feature of human identity, housing sensory organs like the eyes, nose, and mouth, and serving as the primary medium for communication and expression. It is composed of bones, muscles, and skin that work together to produce a wide range of facial expressions, conveying emotions like joy, sadness, or anger. The face’s sensory organs enable vision, smell, taste, and hearing, making it a hub for environmental interaction. Its unique features also play a critical role in individual recognition and social bonding."
        ));
        buttonModelMap.put(binding.btnNose, new BodyPartModel(
                "Nose",
                R.drawable.nose,
                "The nose is the primary organ for breathing and smelling, serving as the entry point for air into the respiratory system. Its external structure, made of cartilage and bone, shapes the face, while the internal nasal cavity filters, warms, and humidifies inhaled air. Olfactory receptors in the nasal cavity detect odors, sending signals to the brain for smell perception, which is closely tied to memory and emotion. The nose also plays a role in speech, contributing to resonance and sound production."
        ));
        buttonModelMap.put(binding.btnLip, new BodyPartModel(
                "Lips",
                R.drawable.lips,
                "Lips are soft, movable structures surrounding the mouth, essential for speech, eating, and facial expression. Composed of muscle, connective tissue, and sensitive skin, lips are highly flexible, allowing precise movements for articulating words and consuming food. Their rich nerve supply makes them sensitive to touch, temperature, and pain, contributing to sensory exploration. Lips also play a role in emotional expression, with movements like smiling or pursing conveying feelings."
        ));
        buttonModelMap.put(binding.btnNeck, new BodyPartModel(
                "Neck",
                R.drawable.neck,
                "The neck is a flexible structure that supports the head and connects it to the torso, housing critical anatomical components like the cervical spine, trachea, and major blood vessels. Comprising seven cervical vertebrae, the neck allows for a wide range of head movements, including rotation, flexion, and extension. Muscles like the sternocleidomastoid and trapezius provide strength and mobility, enabling posture and head positioning. The neck also protects vital structures, such as the carotid arteries and jugular veins, essential for blood flow to and from the brain."
        ));
        buttonModelMap.put(binding.btnArm, new BodyPartModel(
                "Arm",
                R.drawable.arm,
                "Arms are upper limb structures that enable lifting, carrying, and interacting with the environment, extending from the shoulder to the hand. Comprising the humerus in the upper arm and the radius and ulna in the forearm, arms are powered by muscles like the biceps, triceps, and deltoids, which facilitate a wide range of motions. The arm’s flexibility and strength make it essential for tasks like throwing, pushing, or embracing. Its sensory nerves also provide tactile feedback, enhancing interaction with objects."
        ));
        buttonModelMap.put(binding.btnEye, new BodyPartModel(
                "Eye",
                R.drawable.eye,
                "The eye is a complex organ responsible for vision, detecting light and converting it into electrical signals for the brain to interpret. Comprising structures like the cornea, lens, retina, and optic nerve, the eye functions like a camera, focusing light to create images. The iris controls light entry, while the retina’s photoreceptors (rods and cones) process light and color. Vision is critical for navigation, communication, and survival, making the eye one of the most vital sensory organs."
        ));
        buttonModelMap.put(binding.btnChest, new BodyPartModel(
                "Chest",
                R.drawable.chest,
                "The chest, or thorax, is a bony and muscular cavity that protects vital organs like the heart and lungs while facilitating respiration. Enclosed by the rib cage, sternum, and thoracic vertebrae, the chest provides structural support and flexibility for breathing. Muscles like the pectoralis major and intercostals drive chest expansion and contraction, enabling air intake and exhalation. The chest also houses major blood vessels and the thymus, which supports immune function."
        ));
        buttonModelMap.put(binding.btnStomach, new BodyPartModel(
                "Stomach",
                R.drawable.stomach,
                "The stomach is a muscular, J-shaped organ in the digestive system that breaks down food using enzymes and acids before passing it to the intestines. Located in the upper abdomen, it receives food from the esophagus and churns it into a semi-liquid form called chyme. The stomach’s lining secretes gastric juices, including hydrochloric acid and pepsin, which digest proteins and kill pathogens. Its role in digestion is critical for nutrient absorption and energy production."
        ));

        // Set click listeners for body part buttons
        for (Map.Entry<View, BodyPartModel> entry : buttonModelMap.entrySet()) {
            View button = entry.getKey();
            BodyPartModel model = entry.getValue();
            button.setOnClickListener(v -> {

                selectedBodyPart = model;
                lastSelectedButton = button;
                binding.btnNext.setEnabled(true); // Enable Next button
            });
        }

        // Next button click listener
        binding.btnNext.setOnClickListener(v -> {
            if (selectedBodyPart != null) {
                // Show ad with cooldown
                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                long lastAdTime = sharedPreferences.getLong("last_ad_time", 0);
                long currentTime = System.currentTimeMillis();
                if (currentTime - lastAdTime > 30_000) { // 30-second cooldown
                    WebNavigationUtils.WebInterstitial(this);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putLong("last_ad_time", currentTime);
                    editor.apply();
                }
                openDetails(selectedBodyPart.getTitle(), selectedBodyPart.getImageResId(), selectedBodyPart.getDescription());
            } else {
                Toast.makeText(this, "Please select a body part", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openDetails(String title, int imageResId, String description) {
        Intent intent = new Intent(this, BodyPartDetailActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("image", imageResId);
        intent.putExtra("description", description);
        startActivity(intent);
    }

    @Override
    protected void myBackActivity() {
        myBackActivity();
    }

}