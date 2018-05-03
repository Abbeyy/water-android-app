package assessment.c1714546.c1714546assessment.tipsAboutWaterContent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import assessment.c1714546.c1714546assessment.R;

/**
 * Created by c1714546 on 5/3/2018.
 */

public class TipsFragmentTwo extends Fragment implements View.OnClickListener, View.OnLongClickListener {
    private AppCompatEditText emailEditText;
    private AppCompatEditText messageEditText;

    public TipsFragmentTwo() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tips_fragment_two_layout, container, false);

        this.emailEditText = (AppCompatEditText)v.findViewById(R.id.email_content);
        this.emailEditText.setOnClickListener(this);
        this.messageEditText = (AppCompatEditText)v.findViewById(R.id.message_content);
        this.messageEditText.setOnClickListener(this);

        AppCompatButton sendEmailBtn = (AppCompatButton)v.findViewById(R.id.send_email_btn);
        sendEmailBtn.setOnClickListener(this);
        AppCompatButton resetBtn = (AppCompatButton)v.findViewById(R.id.reset_btn);
        resetBtn.setOnLongClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        int viewComponentId = v.getId();

        switch (viewComponentId) {
            case R.id.email_content :
                this.emailEditText.setText("");
                break;
            case R.id.message_content :
                this.messageEditText.setText("");
                break;
            case R.id.send_email_btn :
                sendAnEmail();
                break;
            default :
                break;
        }
    }

    @Override
    public boolean onLongClick(View v) {
        this.emailEditText.setText("");
        this.messageEditText.setText("");
        return false;
    }

    public void sendAnEmail() {
        //Gather Email Address.
        String emailAddress = this.emailEditText.getText().toString();
        if (emailAddress != "") {

            //If an email address has been gathered, check for message content.
            String text = this.messageEditText.getText().toString();
            if (text != "") {
                //Message has been gathered, now create the intent.
                Intent anEmailIntent = new Intent(Intent.ACTION_SEND);

                //Define the basic data of the intent.
                anEmailIntent.setData(Uri.parse("mailto:"));
                anEmailIntent.setType("text/plain");

                //Express to Who
                anEmailIntent.putExtra(Intent.EXTRA_EMAIL, emailAddress);

                //Express the Subject
                anEmailIntent.putExtra(Intent.EXTRA_SUBJECT, "Sharing from WaterLife Android App");

                //Place the message content
                anEmailIntent.putExtra(Intent.EXTRA_TEXT, text);

                //Try acting on the Intent
                try {
                    startActivity(Intent.createChooser(anEmailIntent, "Send email.."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getActivity(), R.string.no_email_client, Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getActivity(), R.string.enter_a_msg_warning, Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(getActivity(), R.string.specify_email_warning, Toast.LENGTH_SHORT).show();
        }
    }

}
